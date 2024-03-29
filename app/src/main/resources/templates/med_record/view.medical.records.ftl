<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>
<script type="application/javascript">
    $("#medrecords").addClass('active');
</script>

<form method="post" action="/medrecord/all">
    <@spring.bind "MedRecords"/>
    <@spring.bind "SearchCriteria"/>

    <div class="page-header">
        <h2>${springMacroRequestContext.getMessage('page.header.medRecords')}</h2>

    </div>

    <div class="row">
    <#-- Search -->
        <div class="panel-group">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse"
                           href="#collapseSearch"><@spring.message "page.button.search"/></a>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-refresh"></span>
                        </button>
                    </h4>
                </div>
                <div id="collapseSearch" class="panel-collapse collapse">
                    <div class="panel-body">
                        <div class="form-group">
                            <label for="patient"
                                   class="col-sm-3 control-label"><@spring.message "page.header.patient"/></label>
                            <div class="col-sm-9">
                                <@spring.formInput "SearchCriteria.fullTextField1" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.fullName')}'" "text"/><@spring.showErrors "<br/>"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="pull-right">
            <@authorize ifAnyGranted="ROLE_OPERATOR">
                <a href="/medrecord/new">
                    <button type="button"
                            class="btn btn-primary">${springMacroRequestContext.getMessage('page.button.medRecord.register')}</button>
                </a>
            </@authorize>
        </div>
    </div>

<#-- Result table -->
    <div class="row">
        <div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>${springMacroRequestContext.getMessage('page.field.medRecord.number')}</th>
                        <th>${springMacroRequestContext.getMessage('page.field.person.fullName')}</th>
                        <th>${springMacroRequestContext.getMessage('page.field.medRecord.created')}</th>
                        <th>${springMacroRequestContext.getMessage('page.actions')}</th>
                    </tr>
                    </thead>
                    <tbody>
                        <#list MedRecords>
                            <#items as record>
                            <tr>
                                <td>${record?counter}</td>
                                <td>${record.number}</td>
                                <td>${record.patient.fullName}</td>
                                <td>${record.createdDate?datetime}</td>
                                <td>
                                    <a href="/medrecord/load/${(record.id)}/view">
                                        <button type="button" class="btn btn-xs btn-default">
                                            <span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;${springMacroRequestContext.getMessage('page.button.view')}
                                        </button>
                                    </a>
                                    <a href="/medrecord/load/${(record.id)}/edit">
                                        <button type="button" class="btn btn-xs btn-success">
                                            <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;${springMacroRequestContext.getMessage('page.button.edit')}
                                        </button>
                                    </a>
                                    <button type="button" class="btn btn-xs btn-danger"
                                            onclick="this.disabled=true;post('/medrecord/${record.id}/delete', {'${_csrf.parameterName}':'${_csrf.token}'})">
                                        <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;${springMacroRequestContext.getMessage('page.button.delete')}
                                    </button>
                                </td>
                            </tr>
                            </#items>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
  <!--  <div class="row">
        <#if MedRecords?size gt 0>
            <ul class="pagination">
                <li>
                    <a href="#">Prev</a>
                </li>
                <li>
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">Next</a>
                </li>
            </ul>
        </#if>
    </div> -->

    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.medRecords"/>