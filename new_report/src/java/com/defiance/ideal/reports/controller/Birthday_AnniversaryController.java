package com.defiance.ideal.reports.controller;

import com.defiance.ideal.reports.dto.Birthday_AnniversaryDTO;
import com.defiance.ideal.reports.dto.Birthday_AnniversaryFilterDTO;
import com.defiance.ideal.reports.service.Birthday_AnniversaryService;
import com.defiance.ideal.reports.service.Birthday_AnniversaryServiceImpl;
import com.defiance.ideal.reports.util.CommonMethods;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author 9000337
 */
public class Birthday_AnniversaryController extends MultiActionController {

    private Date fromSelectedDate = new Date();
    private Date toSelectedDate = new Date();

    public Birthday_AnniversaryController() {
    }

    private String getFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date dddd = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dddd);
    }

    public ModelAndView getBirthdayAnniversary(HttpServletRequest request, HttpServletResponse response, Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO) throws ParseException {
        ModelAndView mvc = new ModelAndView("Birthday_Anniversary");
        final WebApplicationContext ctx = getWebApplicationContext();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            if (birthday_AnniversaryFilterDTO != null && birthday_AnniversaryFilterDTO.getFromSelectedDate() != null) {
                fromSelectedDate = dateFormat.parse(birthday_AnniversaryFilterDTO.getFromSelectedDate());
                birthday_AnniversaryFilterDTO.setFromSelectedDate(dateFormat.format(fromSelectedDate));
            } else {
                String fromSelectedDate = getFirstDay(new Date());
                birthday_AnniversaryFilterDTO.setFromSelectedDate(fromSelectedDate);
            }
            if (birthday_AnniversaryFilterDTO != null && birthday_AnniversaryFilterDTO.getToSelectedDate() != null) {
                toSelectedDate = dateFormat.parse(birthday_AnniversaryFilterDTO.getToSelectedDate());
                birthday_AnniversaryFilterDTO.setToSelectedDate(dateFormat.format(toSelectedDate));
            } else {
                birthday_AnniversaryFilterDTO.setToSelectedDate(dateFormat.format(toSelectedDate));
            }
            fromSelectedDate = dateFormat.parse(birthday_AnniversaryFilterDTO.getFromSelectedDate());
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(fromSelectedDate);
            birthday_AnniversaryFilterDTO.setFromSelectedDateDate(calendar1.get(Calendar.DATE));
            birthday_AnniversaryFilterDTO.setFromSelectedDateMonth(calendar1.get(Calendar.MONTH) + 1);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(toSelectedDate);
            birthday_AnniversaryFilterDTO.setToSelectedDateDate(calendar2.get(Calendar.DATE));
            birthday_AnniversaryFilterDTO.setToSelectedDateMonth(calendar2.get(Calendar.MONTH) + 1);

            String s1 = request.getParameter("birthday_Anniversary");
            if (s1 == null || birthday_AnniversaryFilterDTO.getBirthday_Anniversary().length == 2) {
                Birthday_AnniversaryService basb = (Birthday_AnniversaryServiceImpl) ctx.getBean("Birthday_AnniversaryService");
                List<Birthday_AnniversaryDTO> birthdayDTO = basb.getBirthday(birthday_AnniversaryFilterDTO);
                mvc.addObject("birthdaylist", birthdayDTO);
                mvc.addObject("filterDTO", birthday_AnniversaryFilterDTO);
                Birthday_AnniversaryService bas = (Birthday_AnniversaryServiceImpl) ctx.getBean("Birthday_AnniversaryService");
                List<Birthday_AnniversaryDTO> anniversaryDTO = bas.getAnniversary(birthday_AnniversaryFilterDTO);
                mvc.addObject("filterDTO", birthday_AnniversaryFilterDTO);
                mvc.addObject("anniversarylist", anniversaryDTO);
                String[] s = {"B", "A"};
                birthday_AnniversaryFilterDTO.setBirthday_Anniversary(s);
                mvc.addObject("filterDTO", birthday_AnniversaryFilterDTO);
            } else {
                String selectdFilter = null;
                if (birthday_AnniversaryFilterDTO.getBirthday_Anniversary().length == 1) {
                    selectdFilter = birthday_AnniversaryFilterDTO.getBirthday_Anniversary()[0];

                    if (("A").equals(selectdFilter)) {
                        Birthday_AnniversaryService bas = (Birthday_AnniversaryServiceImpl) ctx.getBean("Birthday_AnniversaryService");
                        List<Birthday_AnniversaryDTO> anniversaryDTO = bas.getAnniversary(birthday_AnniversaryFilterDTO);
                        mvc.addObject("filterDTO", birthday_AnniversaryFilterDTO);
                        mvc.addObject("anniversarylist", anniversaryDTO);
                    }

                    if (("B").equals(selectdFilter)) {
                        Birthday_AnniversaryService bas = (Birthday_AnniversaryServiceImpl) ctx.getBean("Birthday_AnniversaryService");
                        List<Birthday_AnniversaryDTO> birthdayDTO = bas.getBirthday(birthday_AnniversaryFilterDTO);
                        mvc.addObject("birthdaylist", birthdayDTO);
                        mvc.addObject("filterDTO", birthday_AnniversaryFilterDTO);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mvc;
    }

    public ModelAndView excelexportBirthday_Anniversary(HttpServletRequest request, HttpServletResponse response, Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO) throws Exception {
        final WebApplicationContext ctx = getWebApplicationContext();
        Birthday_AnniversaryService bas = (Birthday_AnniversaryServiceImpl) ctx.getBean("Birthday_AnniversaryService");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fromSelectedDate = dateFormat.parse(birthday_AnniversaryFilterDTO.getFromSelectedDate());
            birthday_AnniversaryFilterDTO.setFromSelectedDate(dateFormat.format(fromSelectedDate));
            toSelectedDate = dateFormat.parse(birthday_AnniversaryFilterDTO.getToSelectedDate());
            birthday_AnniversaryFilterDTO.setToSelectedDate(dateFormat.format(toSelectedDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(fromSelectedDate);
        birthday_AnniversaryFilterDTO.setFromSelectedDateDate(calendar1.get(Calendar.DATE));
        birthday_AnniversaryFilterDTO.setFromSelectedDateMonth(calendar1.get(Calendar.MONTH) + 1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(toSelectedDate);
        birthday_AnniversaryFilterDTO.setToSelectedDateDate(calendar2.get(Calendar.DATE));
        birthday_AnniversaryFilterDTO.setToSelectedDateMonth(calendar2.get(Calendar.MONTH) + 1);
        List<Birthday_AnniversaryDTO> birthdayanniversaryDTO = null;
        ArrayList entireList = new ArrayList();
        String selectdFilter = null;
        if (birthday_AnniversaryFilterDTO.getBirthday_Anniversary().length == 1) {
            selectdFilter = birthday_AnniversaryFilterDTO.getBirthday_Anniversary()[0];

            if (("A").equals(selectdFilter)) {
                birthdayanniversaryDTO = bas.getAnniversary(birthday_AnniversaryFilterDTO);
                for (int i = 0; i < birthdayanniversaryDTO.size(); i++) {
                    ArrayList rowDataList = new ArrayList();

                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployee_number()));
                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployeeName()));
                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getSbu()));
                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getWork_Email_Address()));
                    rowDataList.add(new String("" + "--"));
                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getDoj()));
                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getAnniversary()) + "years");
                    rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getStatus()));
                    entireList.add(rowDataList);
                }
                CommonMethods.exportToExcel(response, entireList, "BirthdayAnniversaryReport", "BirthdayAnniversaryReport", "");
            }
        }
        if (("B").equals(selectdFilter)) {
            birthdayanniversaryDTO = bas.getBirthday(birthday_AnniversaryFilterDTO);

            for (int i = 0; i < birthdayanniversaryDTO.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployee_number()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployeeName()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getSbu()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getWork_Email_Address()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getBirthDate()));
                rowDataList.add(new String("" + "--"));
                rowDataList.add(new String("" + "--"));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getStatus()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "BirthdayAnniversaryReport", "BirthdayAnniversaryReport", "");
        }
        if (birthday_AnniversaryFilterDTO.getBirthday_Anniversary().length == 2) {
            selectdFilter = birthday_AnniversaryFilterDTO.getBirthday_Anniversary()[0];
            birthdayanniversaryDTO = bas.getBirthday(birthday_AnniversaryFilterDTO);
            for (int i = 0; i < birthdayanniversaryDTO.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployee_number()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployeeName()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getSbu()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getWork_Email_Address()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getBirthDate()));
                rowDataList.add(new String("" + "--"));
                rowDataList.add(new String("" + "--"));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getStatus()));
                entireList.add(rowDataList);
            }
            birthdayanniversaryDTO = bas.getAnniversary(birthday_AnniversaryFilterDTO);
            for (int i = 0; i < birthdayanniversaryDTO.size(); i++) {
                ArrayList rowDataList = new ArrayList();
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployee_number()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getEmployeeName()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getSbu()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getWork_Email_Address()));
                rowDataList.add(new String("" + "--"));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getDoj()));
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getAnniversary()) + "years");
                rowDataList.add(new String("" + birthdayanniversaryDTO.get(i).getStatus()));
                entireList.add(rowDataList);
            }
            CommonMethods.exportToExcel(response, entireList, "BirthdayAnniversaryReport", "BirthdayAnniversaryReport", "");
        }
        return null;
    }
}
