package com.example.labority_project.Service;

import com.example.labority_project.Api.ApiException;
import com.example.labority_project.Model.Laboratories;
import com.example.labority_project.Model.Orders;
import com.example.labority_project.Model.TestType;
import com.example.labority_project.Model.User;
import com.example.labority_project.Repository.LaboartoriesRepository;
import com.example.labority_project.Repository.TestTypeRepository;
import com.example.labority_project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestTypeService {
    private final TestTypeRepository testTypeRepository;
    private final LaboartoriesRepository laboartoriesRepository;
    private final UserRepository userRepository;

    public List<TestType> getAllTestType(){
        return testTypeRepository.findAll();
    }

    public void addTestType(Integer laboartory_id,TestType testType){
        Laboratories laboratories = laboartoriesRepository.findLaboratoriesById(laboartory_id);

        if (laboratories == null)
            throw new ApiException("Sorry, the laboartory not found");

        testType.setLaboratory(laboratories);
        testTypeRepository.save(testType);
    }

    public void updateTestType(Integer id, TestType testType){
        TestType oldTestType = testTypeRepository.findTestTypeById(id);

        if (oldTestType ==null)
            throw new ApiException("Sorry the test type not found");

        oldTestType.setName(testType.getName());
        oldTestType.setDescription(testType.getDescription());
        oldTestType.setPrice(testType.getPrice());
        oldTestType.setTest_date(testType.getTest_date());
        testTypeRepository.save(oldTestType);
    }

    public void deleteTestType(Integer id){
        TestType deleteTestType = testTypeRepository.findTestTypeById(id);

        if (deleteTestType == null)
            throw new ApiException("Sorry the test type not found");
        deleteTestType.setUsers(null);
        testTypeRepository.delete(deleteTestType);
    }

    public void booking_appointment(Integer user_id, Integer testType_id){
        User user = userRepository.findUserById(user_id);
        TestType testType = testTypeRepository.findTestTypeById(testType_id);
        Laboratories laboratory = laboartoriesRepository.findLaboratoriesById(testType.getLaboratory().getId());


        if (user == null)
            throw new ApiException("Sorry, the user id is wrong");
        else if (testType == null || laboratory == null)
            throw new ApiException("Sorry, the test type id is wrong");

        if (testType.getAppointment())
            throw new ApiException("Sorry, this test type already booked");
        else {
            user.setTestType(testType);
            testType.setAppointment(true);
            user.getLaboratories().add(laboratory);
            laboratory.getUsers().add(user);
        }

        testTypeRepository.save(testType);

        userRepository.save(user);
        laboartoriesRepository.save(laboratory);
    }

    public Double nationalDay_Offer(Integer testType_id) throws ParseException {
        TestType testType = testTypeRepository.findTestTypeById(testType_id);

        if (testType == null)
            throw new ApiException("Sorry , the test id is wrong");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //national day format
        Date nationalDate = dateFormat.parse("2023-09-23");

        // remove the portion of hours, min, seconds,
        Date testDate = getZeroTimeDate(testType.getTest_date());

        if (nationalDate.compareTo(testDate) != 0)
            throw new ApiException("Sorry, can't get a discount because the test date is not equal to national day");


        Double discount = testType.getPrice() - (testType.getPrice() * 0.15);

        if (discount < 0)
            throw new ApiException("Sorry the discount wrong");

        testType.setPrice(discount);
        testTypeRepository.save(testType);

        return discount;
    }

    public Date getZeroTimeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date = calendar.getTime();
        return date;
    }

}
