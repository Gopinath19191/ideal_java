/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dao.Birthday_AnniversaryDao;
import com.defiance.ideal.reports.dto.Birthday_AnniversaryDTO;
import com.defiance.ideal.reports.dto.Birthday_AnniversaryFilterDTO;
import java.util.List;

/**
 *
 * @author 9000337
 */
public class Birthday_AnniversaryServiceImpl implements Birthday_AnniversaryService {

    private Birthday_AnniversaryDao birthday_AnniversaryDao;

    public Birthday_AnniversaryDao getBirthday_AnniversaryDao() {
        return birthday_AnniversaryDao;
    }

    public void setBirthday_AnniversaryDao(Birthday_AnniversaryDao birthday_AnniversaryDao) {
        this.birthday_AnniversaryDao = birthday_AnniversaryDao;
    }

    public List<Birthday_AnniversaryDTO> getBirthday(Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO) {
        System.out.println("service");
        return birthday_AnniversaryDao.getBirthday(birthday_AnniversaryFilterDTO);
    }

    public List<Birthday_AnniversaryDTO> getAnniversary(Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO) {
        System.out.println("service");
        return birthday_AnniversaryDao.getAnniversary(birthday_AnniversaryFilterDTO);
    }
}
