--Time List Procedure by Hariharan.C

CREATE PROCEDURE get_date_list(startdate DATE, enddate DATE)
BEGIN

   declare thisDate DATE;
   set thisDate = startdate;
   drop temporary table if exists time_intervals;
   create temporary table if not exists time_intervals
      (
      dateList DATE
      );

   -- *************************************************************************
   -- Loop through the startdate adding each intval interval until enddate
   -- *************************************************************************
   repeat
      insert into time_intervals select thisDate;
       
      set thisDate = timestampadd(DAY,1,thisDate);
   until thisDate >= enddate
   end repeat;
                 
 END;
 
 
 
 
 
 
 
 
DELIMITER //
CREATE PROCEDURE get_date_list(startdate DATE, enddate DATE)
BEGIN

   declare thisDate DATE;
   set thisDate = startdate;
   drop temporary table if exists time_intervals;
   create temporary table if not exists time_intervals
      (
      dateList DATE
      );

   -- *************************************************************************
   -- Loop through the startdate adding each intval interval until enddate
   -- *************************************************************************
   repeat
      insert into time_intervals select thisDate;
       
      set thisDate = timestampadd(DAY,1,thisDate);
   until thisDate >= enddate
   end repeat;

 END//
 DELIMITER ; 
 
 call get_date_list('2009-01-01','2009-01-31');
 
 select * from time_intervals;