/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.dao;

import com.defiance.ideal.reports.dto.Birthday_AnniversaryDTO;
import com.defiance.ideal.reports.dto.Birthday_AnniversaryFilterDTO;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 *
 * @author 9000337
 */
public class Birthday_AnniversaryDaoImpl extends SqlMapClientDaoSupport implements Birthday_AnniversaryDao {

    List<Birthday_AnniversaryDTO> dto = null;

    public List<Birthday_AnniversaryDTO> getBirthday(Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO) {
        dto = getSqlMapClientTemplate().queryForList("Birthday_Anniversary.getBirthday", birthday_AnniversaryFilterDTO);

        return dto;
    }

    public List<Birthday_AnniversaryDTO> getAnniversary(Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO) {
        dto = getSqlMapClientTemplate().queryForList("Birthday_Anniversary.getAnniversary", birthday_AnniversaryFilterDTO);
        return dto;
    }
}
