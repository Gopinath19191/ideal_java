<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
    <head>
        <script type="text/javascript">
            function reject()
            {
                $('#formExitProcessMain').attr("action", "employeeHome.htm");
                $('#formExitProcessMain').submit();
            }
        </script>
    </head>  
    <body onload="changeTabClass('resigForm');">
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <form action="exitProcess.htm" method="post" id="formExitProcessMain" name="formExitProcessMain">
                <div class="qpdSearch" style="height: auto;background-color: #FFF;">
                    <table width="100%">
                        <tbody>
                            <tr>
                                Dear ${empName},
                                <td width="100%" colspan="2">
                                    <P align="justify">
                                           We regret to note that you have chosen to seek separation from us!!

                                            <br>
                                        </P>
                                        <P align="justify">
                                            We are sure that this is a well considered decision and should you feel the need for another discussion to explore alternate opportunities within us, please feel free to contact the Business Head / HR Team. Kindly note that the entire separation process and related procedural activities are automated and therefore, any separation information recorded or entered by you will be taken as final.

                                            <br>
                                        </P>
                                        <P align="justify">
                                           By accessing this automated tool and selecting the ‘Accept’ button, you agree to be legally bound by the terms and conditions set forth in this undertaking

                                            <br>
                                        </P>
                                        <p align="justify" style="font-style: italic;font-family: Arial;font-size: 12px">
                                            “On leaving the services of Hinduja Tech, you shall not take up a full-time/part time employment with any of our customers for a period of one year. While it is possible that you may have certain hard and soft copies of processes or policies documents and any other project related documents, you are required to surrender all such intellectual property of Hinduja Tech (both hard & soft copy) to your reporting manager / HR. Any breach or violation of these rules would be viewed very seriously. You are expected to maintain utmost confidentiality with regard to affairs of Hinduja Tech and shall maintain confidentiality of any information, instruments, documents, non disclosure agreements signed by you relating to Hinduja Tech that may have come to your knowledge as an employee of Hinduja Tech. Hinduja Tech retains/reserves its right to initiate necessary legal proceedings against such associate who are found to be guilty of violating these rules at any given point in time. With this undertaking, you are also bound to settle all advances, assets taken from us as per the Full and Final Settlement. As a matter of abundant caution, you are advised not to make any copies of part or whole of the processes or policies or e-mail the same to either internal or external sources. With this undertaking you also agree that you shall do the knowledge transfer before leaving the services of the company, failing which Hinduja Tech can initiate legal proceedings against you”.
                                            <br>
                                        </p>
                                </td>
                            </tr>
                            </table>
                                            <table class="qpdSearch buttonAlignment" align="center" style="background-color: #FFF;">
                                                <tbody>
                            <tr>
                                <td width="50%" align="right">
                                    <input type="submit" name="btnAccept" id="btnAccept" value="Accept" style="width: 85px;" class="submitbutton">
                                </td>
                                <td width="50%" align="left">
                                    <input type="button" name="btnDecline" id="btnDecline" value="Decline" class="cancelbutton" onclick="reject()">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
        
            
    </body>
</html>
