<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<#assign layoutOut>

<form class="form-horizontal" method="post">
    <@spring.bind "MedRecord"/>

    <div class="row">
        <div class="page-header">
            <#if (MedRecord.id)??>
                <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.medRecord.edit')}</h2>
            <#else>
                <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.medRecord.new')}</h2>
            </#if>
        </div>

        <div class="pull-right">
            <a href="/medrecord/${(MedRecord.id)!''}">
                <button type="submit" class="btn btn-success">Save</button>
            </a>
        </div>
    </div>

    <div class="row">
    <#--Patient-->
        <div class="col-md-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Patient</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="patient.firstName"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.patient.firstName')}</label>
                        <div class="col-sm-9">
                            <@spring.formInput "MedRecord.patient.firstName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.firstName')}' required autofocus" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.lastName"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.patient.lastName')}</label>
                        <div class="col-sm-9">
                            <@spring.formInput "MedRecord.patient.lastName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.lastName')}' required" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <#-- Medical Record -->
        <div class="col-md-8">
            <div class="form-group">
                <label for="patient.firstName">${springMacroRequestContext.getMessage('page.field.patient.firstName')}</label>
                <@spring.formInput "MedRecord.patient.firstName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.firstName')}' required autofocus" "text"/><@spring.showErrors "<br>"/>
            </div>
            <div class="form-group">
                <label for="patient.lastName">${springMacroRequestContext.getMessage('page.field.patient.lastName')}</label>
                <@spring.formInput "MedRecord.patient.lastName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.lastName')}' required" "text"/><@spring.showErrors "<br>"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="pull-right">
            <a href="/medrecords">
                <button type="button" class="btn btn-danger">Cancel</button>
            </a>
            <a href="/medrecord/${(MedRecord.id)!''}">
                <button type="submit" class="btn btn-success">Save</button>
            </a>
        </div>
    </div>

</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.medRecords"/>