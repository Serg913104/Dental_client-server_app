<#assign sf=JspTaglibs["http://www.springframework.org/tags/form"]>
<head>
    <title>Schedules</title>
    <style type="text/css">

        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
            width: max-content;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body bgcolor="#39CCCC">
<article>
    <h1>

        Add new schedule or update.

    </h1>
    <@sf.form action="/schedules/add" method="post" modelAttribute="schedule">
        <table>
            <tr>
                <td>
                    <div hidden="true">
                        <@sf.label path="scheduleNum">Schedule number</@sf.label>
                        <@sf.input path="scheduleNum"  />
                        <@sf.errors path="scheduleNum"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td><@sf.label path="dentistId">Dentist ID</@sf.label></td>
                <td><@sf.input type="number" onclick="this.select();" path="dentistId"/></td>
                <td><@sf.errors path="dentistId"/></td>
            </tr>
            <tr>
                <td><@sf.label path="dateTickets">Date on ticket</@sf.label></td>
                <td> <@sf.input type="date" path="dateTickets"/></td>
                <td><@sf.errors path="dateTickets"/></td>
            </tr>
            <tr>
                <td><@sf.label path="cab">Cabinet</@sf.label></td>
                <td><@sf.input type="number" onclick="this.select();" path="cab"/></td>
                <td><<@sf.errors path="cab"/>/td>
            </tr>
            <tr>
                <td width="100" height="50">
                    <#if schedule.scheduleNum?has_content>
                        <input type="submit" value="Update schedule"  />
                    <#else>
                        <input type="submit" value="Add schedule"/>
                    </#if>
                </td>
                <td><input type="reset" value="Reset"/>
            </tr>
        </table>
    </@sf.form>
    <#if listSchedule?has_content>
        <p><h1>List of schedules</h1>   <h3>Just <a href="/">click here</a>, if you want back to the menu </h3></p>
        <table class="tg">
            <tr>
                <th width="80">Schedule num</th>
                <th width="120">Dentist surname</th>
                <th width="120">dateTickets</th>
                <th width="100">Cab</th>
                <th width="80">Edit</th>
                <th width="80">Delete</th>
            </tr>
            <#list listSchedule as schedule>
                <tr>
                    <td>${schedule.scheduleNum}</td>
                    <td>${schedule.dentist.surname}</td>
                    <td>${schedule.dateTickets}</td>
                    <td>${schedule.cab}</td>
                    <td>
                        <@sf.form action="/sched/edit" method="post" modelAttribute="schedule">
                            <div hidden="true">
                                <@sf.input type="hidden "name="data" path="scheduleNum" value=schedule.scheduleNum />
                            </div>
                            <input type="submit" value="Edit"/>
                        </@sf.form>
                    </td>
                    <td>
                        <@sf.form action="/sched/remove" method="post" modelAttribute="schedule">
                            <div hidden="true">
                                <@sf.input type="hidden "name="data" path="scheduleNum" value=schedule.scheduleNum />
                            </div>
                            <input type="submit" value="Delete"/>
                        </@sf.form>
                    </td>
                </tr>
            </#list>
        </table>
        <p><h3>Just <a href="/">click here</a>, if you want back to the menu </h3></p>
    <#else>
        <h1>
            <p>List of schedule is empty.</p>
            <p>Click <a href="/schedules">here</a> to cancel.</p>
            <p>If you want back to the menu just <a href="/">click here</a>)</p>
        </h1>
    </#if>
</article>
</body>
</html>
