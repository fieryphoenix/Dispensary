<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>
<script type="application/javascript">
    $("#home").addClass('active');
</script>

<form id="CurrentVisits" class="form-horizontal" method="get" action="/" modelAttribute="Visits">

    <@spring.bind "Visits"/>
    <@spring.bind "VisitStatuses"/>

    <div class="page-header">
        <h2>${springMacroRequestContext.getMessage('page.header.currentVisits')}</h2>
    </div>

  <#-- Visits -->
            <div class="panel panel-default">

                <div class="panel-body" id="visits">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.visit.from"/></th>
                            <th><@spring.message "page.field.visit.to"/></th>
                            <th><@spring.message "page.field.visit.goal"/></th>
                            <th><@spring.message "page.field.visit.status"/></th>
                            <th><@spring.message "page.actions"/></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list Visits>
                                <#items as visit>
                                <tr>
                                    <td>${visit.from}</td>
                                    <td>${visit.to}</td>
                                    <td>${visit.goal}</td>
                                    <td>${VisitStatuses[visit.status]}</td>
                                </tr>
                                </#items>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div> <!-- end of Visit -->

</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.home"/>