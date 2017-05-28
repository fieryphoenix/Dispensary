<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>

<form id="MedRecordForm" class="form-horizontal" method="post" action="/medrecord" modelAttribute="MedRecord">
    <@spring.bind "MedRecord"/>
    <@spring.bind "Sexes"/>
    <@spring.bind "Countries"/>
    <@spring.bind "Ultrasounds"/>

    <div class="page-header">
        <#if (MedRecord.id)??>
            <h2><@spring.message "page.header.medRecord.edit"/></h2>
        <#else>
            <h2><@spring.message "page.header.medRecord.new"/></h2>
        </#if>

    </div>

    <div class="row">
    <#--Patient-->
        <div class="col-md-4">
        <#--Personal Info -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">${springMacroRequestContext.getMessage('page.header.patient')}</h3>
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
                    <div class="form-group">
                        <label for="patient.middleName"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.patient.middleName')}</label>
                        <div class="col-sm-9">
                            <@spring.formInput "MedRecord.patient.middleName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.middleName')}'" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.passportSeries"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.patient.passport')}</label>
                        <div class="col-sm-3">
                            <@spring.formInput "MedRecord.patient.passportSeries" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.passportSeries')}' required" "text"/><@spring.showErrors "<br>"/>
                        </div>
                        <div class="col-sm-6">
                            <@spring.formInput "MedRecord.patient.passportNumber" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.passportNumber')}' required" "text"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.birthDate"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.patient.birthDate')}</label>
                        <div class="col-sm-9">
                            <@spring.formInput "MedRecord.patient.birthDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.birthDate')}' required" "date"/><@spring.showErrors "<br>"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.sex"
                               class="col-sm-3 control-label">${springMacroRequestContext.getMessage('page.field.patient.sex')}</label>
                        <div class="col-sm-9">
                            <@spring.formSingleSelect "MedRecord.patient.sex", Sexes, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.patient.sex')}' required">
                            </@spring.formSingleSelect>
                            <@spring.showErrors "<br>"/>
                        </div>
                    </div>
                </div>
            </div>
        <#-- Address -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><@spring.message "page.header.patient.address"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <address>
                        <div class="form-group">
                            <label for="patient.address1.country"
                                   class="col-sm-3 control-label"><@spring.message "page.field.patient.address.country"/></label>
                            <div class="col-sm-9">
                                <@spring.formSingleSelect "MedRecord.patient.address1.country", Countries, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.patient.address.country')}' required">
                                </@spring.formSingleSelect>
                            <@spring.showErrors "<br>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.city"
                                   class="col-sm-3 control-label"><@spring.message "page.field.patient.address.city"/></label>
                            <div class="col-sm-9">
                                <@spring.formInput "MedRecord.patient.address1.city" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.address.city')}' required" "text"/><@spring.showErrors "<br>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.street"
                                   class="col-sm-3 control-label"><@spring.message "page.field.patient.address.street"/></label>
                            <div class="col-sm-9">
                                <@spring.formInput "MedRecord.patient.address1.street" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.address.street')}' required" "text"/><@spring.showErrors "<br>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.house"
                                   class="col-sm-3 control-label"><@spring.message "page.field.patient.address.house"/></label>
                            <div class="col-sm-9">
                                <@spring.formInput "MedRecord.patient.address1.house" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.address.house')}' required" "text"/><@spring.showErrors "<br>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.flat"
                                   class="col-sm-3 control-label"><@spring.message "page.field.patient.address.flat"/></label>
                            <div class="col-sm-9">
                                <@spring.formInput "MedRecord.patient.address1.flat" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.address.flat')}'" "text"/><@spring.showErrors "<br>"/>
                            </div>
                        </div>
                    </address>

                <#-- Phones -->
                    <div class="form-group">
                        <label for="patient.phone"
                               class="col-sm-3 control-label"><@spring.message "page.field.patient.phones"/></label>
                        <div class="col-sm-9">
                            <div class="list-group col-sm-12">
                                <#list MedRecord.patient.phones>
                                    <#items as phone>
                                        <div class="row">
                                            <div class="col-sm-10 vcenter">
                                                <@spring.formInput "MedRecord.patient.phones[${phone_index}]" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.patient.phone')}'" "text"/><@spring.showErrors "<br>"/>
                                                <#--@formatter:off-->
                                            </div><!--
                                            --><div class="col-sm-2 vcenter">
                                            <#--@formatter:on-->
                                                <#if !readOnlyForm>
                                                    <a href="#"
                                                       onclick="this.disabled=true;post('/medrecord/deletePhone/${phone_index}', null, 'post', 'MedRecordForm')">
                                                        <span class="glyphicon glyphicon-trash"></span>
                                                    </a>
                                                </#if>
                                        </div>
                                        </div>
                                    </#items>
                                </#list>
                            </div>
                            <#if !readOnlyForm>
                                <button type="button" class="btn btn-xs"
                                        onclick="this.disabled=true;post('/medrecord/addPhone', null, 'post', 'MedRecordForm')">
                                    <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                                </button>
                            </#if>
                        </div>
                    </div>

                </div>
            </div> <!-- end of Address -->
        </div>
    <#--End Patient-->

    <#-- Medical Record -->
        <div class="col-md-8">
            <@spring.formHiddenInput "MedRecord.id"/>
        <#-- Deseases -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><@spring.message "page.header.patient.diseases"/></h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.disease.from"/></th>
                            <th><@spring.message "page.field.disease.to"/></th>
                            <th><@spring.message "page.field.disease.name"/></th>
                            <th><@spring.message "page.field.disease.notes"/></th>
                            <th><@spring.message "page.actions"/></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list MedRecord.diseases>
                                <#items as disease>
                                <tr>
                                    <td>
                                        <@spring.formInput "MedRecord.diseases[${disease_index}].from" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.from')}' required" "date"/><@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <@spring.formInput "MedRecord.diseases[${disease_index}].to" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.to')}" "date"/><@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <@spring.formInput "MedRecord.diseases[${disease_index}].name" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.name')}' required" "text"/><@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <@spring.formTextarea "MedRecord.diseases[${disease_index}].notes" "rows='5' cols='80' class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.notes')}'" /><@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <#if !readOnlyForm>
                                            <a href="#"
                                               onclick="this.disabled=true;post('/medrecord/deleteDisease/${disease_index}', null, 'post', 'MedRecordForm')">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                        </#if>
                                    </td>
                                </tr>
                                </#items>
                            </#list>

                        </tbody>
                    </table>
                    <#if !readOnlyForm>
                        <button type="button" class="btn btn-xs"
                                onclick="this.disabled=true;post('/medrecord/addDisease', null, 'post', 'MedRecordForm')">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                        </button>
                    </#if>
                </div>
            </div> <!-- end of Diseases -->

        <#-- Ultrasounds -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><@spring.message "page.header.patient.ultrasounds"/></h3>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.ultrasound.date"/></th>
                            <th><@spring.message "page.field.ultrasound.type"/></th>
                            <th><@spring.message "page.field.ultrasound.notes"/></th>
                            <th><@spring.message "page.actions"/></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list MedRecord.ultrasounds>
                                <#items as ultrasound>
                                <tr>
                                    <td>
                                        <@spring.formInput "MedRecord.ultrasounds[${ultrasound_index}].recordDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.ultrasound.date')}' required" "date"/><@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <@spring.formSingleSelect "MedRecord.ultrasounds[${ultrasound_index}].type", Ultrasounds, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.ultrasound.type')}' required">
                                        </@spring.formSingleSelect>
                                        <@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <@spring.formTextarea "MedRecord.ultrasounds[${ultrasound_index}].notes" "rows='5' cols='80' class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.ultrasound.notes')}'" /><@spring.showErrors "<br>"/>
                                    </td>
                                    <td>
                                        <#if !readOnlyForm>
                                            <a href="#"
                                               onclick="this.disabled=true;post('/medrecord/deleteDisease/${ultrasound_index}', null, 'post', 'MedRecordForm')">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                        </#if>
                                    </td>
                                </tr>
                                </#items>
                            </#list>

                        </tbody>
                    </table>
                    <#if !readOnlyForm>
                        <button type="button" class="btn btn-xs"
                                onclick="this.disabled=true;post('/medrecord/addUltrasound', null, 'post', 'MedRecordForm')">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                        </button>
                    </#if>
                </div>
            </div> <!-- end of Ultrasounds -->
        </div>
    <#-- End of Medical Record -->
    </div>

<#-- Controls -->
    <div class="row">
        <div class="pull-right">
            <a href="/medrecords">
                <button type="button"
                        class="btn btn-sm btn-danger"><@spring.message "page.button.cancel"/></button>
            </a>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <#if !readOnlyForm>
                <button type="submit"
                        class="btn btn-success"><@spring.message "page.button.save"/></button>
            </#if>
        </div>
    </div>

<#-- Read only -->
    <@spring.bind "readOnlyForm"/>
    <input type="hidden"
           id="readOnlyForm"
           value="${readOnlyForm?c}"/>
</form>

</#assign>

<#function title>
    <#if (MedRecord.id)??>
        <#return 'page.title.medRecord.edit'/>
    <#else>
        <#return 'page.title.medRecord.register'/>
    </#if>
</#function>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="${title()}"/>