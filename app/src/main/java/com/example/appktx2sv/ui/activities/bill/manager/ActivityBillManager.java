package com.example.appktx2sv.ui.activities.bill.manager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appktx2sv.AppKtx;
import com.example.appktx2sv.data.dto.BillDto;
import com.example.appktx2sv.data.dto.SemesterDto;
import com.example.appktx2sv.data.model.BillStatusModel;
import com.example.appktx2sv.data.model.BillTypeModel;
import com.example.appktx2sv.databinding.ActivityBillManagerBinding;
import com.example.appktx2sv.ui.activities.bill.BillStatus;
import com.example.appktx2sv.ui.activities.bill.BillType;
import com.example.appktx2sv.ui.dialog.MyDialog;

import java.util.ArrayList;
import java.util.List;

public class ActivityBillManager extends AppCompatActivity {

    BillManagerHandler handler;
    ActivityBillManagerBinding binding;
    BillStatus billStatus;
    public Integer idSemester = null;
    List<SemesterDto> semesterDtoList;
    List<BillDto> allBills = new ArrayList<>(), displayBills = new ArrayList<>();
    String searchText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityBillManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.handler =  new BillManagerHandler(this);
        setEvent();

    }
    @Override
    protected void onResume() {
        super.onResume();
        this.handler.getAllSemester();
    }
    private void setEvent() {

        binding.semesterSelector.setOnSelected(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(ActivityBillManager.this.semesterDtoList != null){
                    SemesterDto semesterDto = ActivityBillManager.this.semesterDtoList.get(position);
                    ActivityBillManager.this.onSelectSemester(semesterDto.getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<BillTypeModel> billTypeModels = new ArrayList<>();
        billTypeModels.add(new BillTypeModel(0, "All"));
        billTypeModels.add(new BillTypeModel(1, "Room bill"));
        billTypeModels.add(new BillTypeModel(2, "Electric water bill"));

        List<BillStatusModel> billStatusModels = new ArrayList<>();
        billStatusModels.add(new BillStatusModel(0, null));
        billStatusModels.add(new BillStatusModel(1, true));
        billStatusModels.add(new BillStatusModel(2, false));

        binding.billStatus.setData(billStatusModels, (obj) -> {
            BillStatusModel b = (BillStatusModel)obj;
            if(((BillStatusModel) obj).getIsPay() == null){
                return "All";
            }
            return  b.getIsPay() ? "Paid" : "Unpaid";
        });

        binding.billStatus.setOnSelected(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    setBillStatus(BillStatus.ALL);
                }
                else if(position == 1){
                    setBillStatus(BillStatus.PAID);
                }
                else if(position == 2){
                    setBillStatus(BillStatus.UN_PAID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.findBill.setOnClickListener(v -> {
            MyDialog myDialog = MyDialog.CreateFindBillByIdDialog(this, objs -> {
                    Integer idBill = (Integer) objs[0];
                    this.handler.getBillDetail(idBill);
            });

            myDialog.show();
        });
    }
    /*
     * Gọi khi thay đổi bill status [paid / un paid]
     * */
    public void setBillStatus(BillStatus billStatus){
        this.billStatus = billStatus;
        this.displayBills = filterBillByStatus(this.allBills, billStatus);
        this.handler.setDisplayBills(this.displayBills);
    }
    /*
    * Gọi khi selected semester
    * Khi thay đổi semester sẽ gọi api lấy các bill tương ứng với semester đó
    * */
    public void onSelectSemester(Integer idSemester){
        this.idSemester = idSemester;
        this.handler.getAllMyBill(idSemester, AppKtx.userDto.getId());
    }
    /*
    * Lọc dữ liệu với bill status [paid / un paid]
    * */
    public List<BillDto> filterBillByStatus(List<BillDto> billDtoList, BillStatus billStatus){
        List<BillDto> rsl = new ArrayList<>();
        for (BillDto billDto: billDtoList) {
            if(billStatus.equals(BillStatus.PAID)){
                if(billDto.getStatus()){
                    rsl.add(billDto);
                }
            }
            else if (billStatus.equals(BillStatus.UN_PAID)){
                if(!billDto.getStatus()){
                    rsl.add(billDto);
                }
            }
            else{
                rsl.add(billDto);
            }
        }
        return  rsl;
    }
    /*
     * Lọc dữ liệu với bill status [paid / un paid]
     * */
    public List<BillDto> filterBillByStatus(List<BillDto> billDtoList){
        List<BillDto> rsl = new ArrayList<>();
        for (BillDto billDto: billDtoList) {
            if(billStatus.equals(BillStatus.PAID)){
                if(billDto.getStatus()){
                    rsl.add(billDto);
                }
            }
            else if (billStatus.equals(BillStatus.UN_PAID)){
                if(!billDto.getStatus()){
                    rsl.add(billDto);
                }
            }
            else{
                rsl.add(billDto);
            }
        }
        return  rsl;
    }
    /*
     * Lọc dữ liệu với search text
     * */
    public List<BillDto> filterBillBySearchText(List<BillDto> billDtoList){
        List<BillDto> rsl = new ArrayList<>();
        for (BillDto billDto: billDtoList) {
            if(
                    (billDto.getRoomName()!= null && billDto.getRoomName().contains(this.searchText)) ||
                    (billDto.getEmail()!= null && billDto.getEmail().contains(this.searchText)) ||
                            (this.searchText.equals(""))
            ){
                rsl.add(billDto);
            }
        }
        return  rsl;
    }
    /*
    * Gọi khi search text
    * */
    public void setSearchText(String newText){
        this.searchText = newText;
//        this.handler.setDisplayBills(this.allBills);
    }
}
