/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defiance.ideal.reports.service;

import com.defiance.ideal.reports.dto.Birthday_AnniversaryDTO;
import com.defiance.ideal.reports.dto.Birthday_AnniversaryFilterDTO;
import java.util.List;

/**
 *
 * @author 9000337
 */
public interface Birthday_AnniversaryService {

    public List<Birthday_AnniversaryDTO> getBirthday(Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO);

    public List<Birthday_AnniversaryDTO> getAnniversary(Birthday_AnniversaryFilterDTO birthday_AnniversaryFilterDTO);
}
