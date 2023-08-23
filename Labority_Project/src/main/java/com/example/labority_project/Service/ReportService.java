package com.example.labority_project.Service;

import com.example.labority_project.Api.ApiException;
import com.example.labority_project.DTO.ReportDTO;
import com.example.labority_project.DTO.UserDto;
import com.example.labority_project.Model.Orders;
import com.example.labority_project.Model.Report;
import com.example.labority_project.Model.User;
import com.example.labority_project.Model.UserDetails;
import com.example.labority_project.Repository.OrderRepository;
import com.example.labority_project.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    private  final OrderRepository orderRepository;

    public List<Report> getAllReport(){

        return reportRepository.findAll();
    }

    public void addReport(ReportDTO reportDTO){
        Orders findOrder=orderRepository.findOrderById(reportDTO.getOrder_id());
        if (findOrder == null){
            throw new ApiException("Sorry, can't add report beacuse does not find order ");
        }

        if (findOrder.getOrder_status().equals("confirmed")) {
            Report report = new Report(null, reportDTO.getTitle(), reportDTO.getReportContent(), findOrder);
            reportRepository.save(report);
            assignReportToOrder(report.getId(),findOrder.getId());
        }else
            throw new ApiException("Sorry, the order status must be confirmed to show the report");
    }


    public void updateReport(ReportDTO reportDTO){
        Report report = reportRepository.findReportById(reportDTO.getOrder_id());
        if(report ==null){
            throw new ApiException("sorry can't find the report");
        }

        report.setTitle(reportDTO.getTitle());
        report.setReportContent(reportDTO.getReportContent());
        reportRepository.save(report);
    }
    public void deleteReport(Integer id){
        Report report = reportRepository.findReportById(id);

        if (report == null)
            throw new ApiException("Sorry, the report is is wrong");

        report.setOrders(null);
        reportRepository.delete(report);
    }

    public void assignReportToOrder(Integer report_id,Integer order_id){

        Orders orders=orderRepository.findOrderById(order_id);
        Report report =reportRepository.findReportById(report_id);

        if (orders ==null || report==null){
            throw  new ApiException("Can not Assign ids could be wrong");
        }
        report.setOrders(orders);
    }
}
