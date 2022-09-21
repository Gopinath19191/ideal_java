/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.defiance.ideal.shared;

import org.apache.beehive.controls.api.bean.ControlExtension;
import org.apache.beehive.controls.system.jdbc.JdbcControl;

/**
 *
 * @author 14053
 */
@ControlExtension
//@JdbcControl.ConnectionDataSource(jndiName = "ideal")
//@JdbcControl.ConnectionDataSource(jndiName = "java:comp/env/ideal_leave")
@JdbcControl.ConnectionDataSource(jndiName = "ideal_aa")
//@JdbcControl.ConnectionDataSource(jndiName = "ideal_demo")
//@JdbcControl.ConnectionDataSource(jndiName = "ideal_qpd")
public interface DBConnectivity extends JdbcControl {
}
