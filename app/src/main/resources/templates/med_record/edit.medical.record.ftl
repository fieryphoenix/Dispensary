<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>
<script type="application/javascript">
    $("#medrecords").addClass('active');
    $(function () {
        $(".datetimepicker").datetimepicker({locale: '${.locale}'});
        $('.input-group.datepicker').datepicker({language: "${.locale}"});
    });
</script>

<#macro controls>
<#-- Controls -->
<div class="row hidden-print">
    <div class="pull-right">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <a href="/medrecord/all">
            <button type="button"
                    class="btn btn-sm btn-danger"><@spring.message "page.button.cancel"/></button>
        </a>
        <a href="javascript: if(window.print) window.print()">
            <button type="button"
                    class="btn btn-sm btn-info"><@spring.message "page.button.print"/></button>
        </a>
        <@readonlyOrElse readOnlyForm>
        <button type="submit"
                class="btn btn-success"><@spring.message "page.button.save"/></button>
    </@readonlyOrElse>
    </div>
</div>
</#macro>

<form id="MedRecordForm" class="form-horizontal" method="post" action="/medrecord/save" modelAttribute="MedRecord">
    <@spring.bind "MedRecord"/>
    <@spring.bind "Sexes"/>
    <@spring.bind "Countries"/>
    <@spring.bind "Ultrasounds"/>
    <@spring.bind "Doctors"/>
    <@spring.bind "VisitStatuses"/>

    <div class="page-header">
        <#if ((MedRecord.id!"")?length > 0) >
            <h2><@spring.message "page.header.medRecord.edit"/></h2>
        <#else>
            <h2><@spring.message "page.header.medRecord.new"/></h2>
        </#if>

    </div>
    <@controls/>

    <#if !readOnlyForm>
        <#assign labelClass = 'control-label'>
        <#assign datetimepicker = 'input-group date datetimepicker'>
        <#assign datepicker = 'input-group date datepicker'>
    <#else>
        <#assign labelClass = ''>
        <#assign datetimepicker = ''>
        <#assign datepicker = ''>
    </#if>

    <div class="row">
    <#--Person-->
        <div class="col-md-3">
        <#--Personal Info -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">${springMacroRequestContext.getMessage('page.header.patient')}</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="patient.firstName"
                               class="col-sm-3 ${labelClass}">${springMacroRequestContext.getMessage('page.field.person.firstName')}</label>
                        <div class="col-sm-9">
                            <@readonlyOrElse readOnlyForm MedRecord.patient.firstName>
                            <@spring.formInput "MedRecord.patient.firstName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.firstName')}' required autofocus" "text"/><@spring.showErrors "<br/>"/>
                            </@readonlyOrElse>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.lastName"
                               class="col-sm-3 ${labelClass}">${springMacroRequestContext.getMessage('page.field.person.lastName')}</label>
                        <div class="col-sm-9">
                            <@readonlyOrElse readOnlyForm MedRecord.patient.lastName><@spring.formInput "MedRecord.patient.lastName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.lastName')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.middleName"
                               class="col-sm-3 ${labelClass}">${springMacroRequestContext.getMessage('page.field.person.middleName')}</label>
                        <div class="col-sm-9">
                            <@readonlyOrElse readOnlyForm MedRecord.patient.middleName><@spring.formInput "MedRecord.patient.middleName" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.middleName')}'" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.passportSeries"
                               class="col-sm-3 ${labelClass}">${springMacroRequestContext.getMessage('page.field.person.passport')}</label>
                        <div class="col-sm-3">
                            <@readonlyOrElse readOnlyForm MedRecord.patient.passportSeries><@spring.formInput "MedRecord.patient.passportSeries" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.passportSeries')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                        </div>
                        <div class="col-sm-6">
                            <@readonlyOrElse readOnlyForm MedRecord.patient.passportNumber><@spring.formInput "MedRecord.patient.passportNumber" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.passportNumber')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.birthDate"
                               class="col-sm-3 ${labelClass}">${springMacroRequestContext.getMessage('page.field.person.birthDate')}</label>
                        <div class="col-sm-9 ${datepicker}">
                            <@readonlyOrElse readOnlyForm MedRecord.patient.birthDate!?date><@spring.formInput "MedRecord.patient.birthDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.birthDate')}' required" /><@spring.showErrors "<br/>"/>
                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                            </@readonlyOrElse>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="patient.sex"
                               class="col-sm-3 ${labelClass}">${springMacroRequestContext.getMessage('page.field.person.sex')}</label>
                        <div class="col-sm-9">
                            <@readonlyOrElse readOnlyForm Sexes[MedRecord.patient.sex!'']><@spring.formSingleSelect "MedRecord.patient.sex", Sexes, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.person.sex')}' required">
                            </@spring.formSingleSelect>
                            <@spring.showErrors "<br/>"/></@readonlyOrElse>
                        </div>
                    </div>
                </div>
            </div>
        <#-- Address -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <@spring.message "page.header.patient.address"/>
                        <a class="accordion-toggle hidden-print" data-toggle="collapse" href="#address"></a>
                    </h3>
                </div>
                <div class="panel-body in" id="address">
                    <address>
                        <div class="form-group">
                            <label for="patient.address1.country"
                                   class="col-sm-3 ${labelClass}"><@spring.message "page.field.person.address.country"/></label>
                            <div class="col-sm-9">
                                <@readonlyOrElse readOnlyForm Countries[MedRecord.patient.address1.country]><@spring.formSingleSelect "MedRecord.patient.address1.country", Countries, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.person.address.country')}' required">
                                </@spring.formSingleSelect>
                            <@spring.showErrors "<br/>"/></@readonlyOrElse>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.city"
                                   class="col-sm-3 ${labelClass}"><@spring.message "page.field.person.address.city"/></label>
                            <div class="col-sm-9">
                                <@readonlyOrElse readOnlyForm MedRecord.patient.address1.city><@spring.formInput "MedRecord.patient.address1.city" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.address.city')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.street"
                                   class="col-sm-3 ${labelClass}"><@spring.message "page.field.person.address.street"/></label>
                            <div class="col-sm-9">
                                <@readonlyOrElse readOnlyForm MedRecord.patient.address1.street><@spring.formInput "MedRecord.patient.address1.street" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.address.street')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.house"
                                   class="col-sm-3 ${labelClass}"><@spring.message "page.field.person.address.house"/></label>
                            <div class="col-sm-9">
                                <@readonlyOrElse readOnlyForm MedRecord.patient.address1.house><@spring.formInput "MedRecord.patient.address1.house" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.address.house')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="patient.address1.flat"
                                   class="col-sm-3 ${labelClass}"><@spring.message "page.field.person.address.flat"/></label>
                            <div class="col-sm-9">
                                <@readonlyOrElse readOnlyForm MedRecord.patient.address1.flat><@spring.formInput "MedRecord.patient.address1.flat" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.address.flat')}'" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                            </div>
                        </div>
                    </address>

                <#-- Phones -->
                    <div class="form-group">
                        <label for="patient.phone"
                               class="col-sm-3 ${labelClass}"><@spring.message "page.field.person.phones"/></label>
                        <div class="col-sm-9">
                            <div class="list-group col-sm-12">
                                <#list MedRecord.patient.phones>
                                    <#items as phone>
                                        <div class="row">
                                            <div class="col-sm-10 vcenter">
                                                <@readonlyOrElse readOnlyForm MedRecord.patient.phones[phone_index]><@spring.formInput "MedRecord.patient.phones[${phone_index}]" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.person.phone')}'" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
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
                            <@readonlyOrElse readOnlyForm>
                                <button type="button" class="btn btn-xs"
                                        onclick="this.disabled=true;post('/medrecord/addPhone', null, 'post', 'MedRecordForm')">
                                    <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                                </button>
                            </@readonlyOrElse>
                        </div>
                    </div>

                </div>
            </div> <!-- end of Address -->
        </div>
    <#--End Person-->

    <#-- Medical Record -->
        <div class="col-md-9">
            <@spring.formHiddenInput "MedRecord.id"/>

        <#-- Visits -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <@spring.message "page.header.patient.visits"/>
                        <a class="accordion-toggle hidden-print" data-toggle="collapse" href="#visits"></a>
                    </h3>
                </div>
                <div class="panel-body in" id="visits">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.visit.from"/></th>
                            <th><@spring.message "page.field.visit.to"/></th>
                            <th><@spring.message "page.field.visit.goal"/></th>
                            <th><@spring.message "page.field.visit.visitTo"/></th>
                            <th><@spring.message "page.field.visit.status"/></th>
                            <@readonlyOrElse readOnlyForm><th><@spring.message "page.actions"/></th></@readonlyOrElse>
                        </tr>
                        </thead>
                        <tbody>
                            <#list MedRecord.visits>
                                <#items as visit>
                                <tr>
                                    <td>
                                        <div class='${datetimepicker}' id='${datetimepicker}_from_${visit_index}'>
                                            <@readonlyOrElse readOnlyForm MedRecord.visits[visit_index].from.format('dd-MM-yyyy HH:mm')><@spring.formInput "MedRecord.visits[${visit_index}].from" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.visit.from')}' required" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                            <@spring.showErrors "<br/>"/>
                                            </@readonlyOrElse>
                                        </div>
                                    </td>
                                    <td>
                                        <div class='${datetimepicker}' id='${datetimepicker}_to_${visit_index}'>
                                            <@readonlyOrElse readOnlyForm MedRecord.visits[visit_index].to.format('dd-MM-yyyy HH:mm')><@spring.formInput "MedRecord.visits[${visit_index}].to" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.visit.to')}' required"/>
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                            <@spring.showErrors "<br/>"/>
                                            </@readonlyOrElse>
                                        </div>
                                    </td>
                                    <td>
                                        <@readonlyOrElse readOnlyForm MedRecord.visits[visit_index].goal><@spring.formInput "MedRecord.visits[${visit_index}].goal" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.visit.goal')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                                    </td>
                                    <td>
                                        <@readonlyOrElse readOnlyForm Doctors[MedRecord.visits[visit_index].visitToID!'']><@spring.formSingleSelect "MedRecord.visits[${visit_index}].visitToID", Doctors, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.visit.visitTo')}' required">
                                        </@spring.formSingleSelect>
                                        <@spring.showErrors "<br/>"/>
                                        </@readonlyOrElse>
                                    </td>
                                    <td>
                                        <@readonlyOrElse readOnlyForm VisitStatuses[MedRecord.visits[visit_index].status]><@spring.formSingleSelect "MedRecord.visits[${visit_index}].status", VisitStatuses, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.visit.status')}' required">
                                        </@spring.formSingleSelect>
                                        <@spring.showErrors "<br/>"/>
                                        </@readonlyOrElse>
                                    </td>
                                    <@readonlyOrElse readOnlyForm><td>
                                        <#if !readOnlyForm>
                                            <a href="#"
                                               onclick="this.disabled=true;post('/medrecord/cancelVisit/${visit_index}', null, 'post', 'MedRecordForm')">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                        </#if>
                                    </td></@readonlyOrElse>
                                </tr>
                                </#items>
                            </#list>
                        </tbody>
                    </table>
                    <@readonlyOrElse readOnlyForm>
                        <button type="button" class="btn btn-xs"
                                onclick="this.disabled=true;post('/medrecord/addVisit', null, 'post', 'MedRecordForm')">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                        </button>
                    </@readonlyOrElse>
                </div>
            </div> <!-- end of Visit -->

        <#-- Diseases -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <@spring.message "page.header.patient.diseases"/>
                        <a class="accordion-toggle hidden-print" data-toggle="collapse" href="#diseases"></a>
                    </h3>
                </div>
                <div class="panel-body in" id="diseases">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.disease.from"/></th>
                            <th><@spring.message "page.field.disease.to"/></th>
                            <th><@spring.message "page.field.disease.name"/></th>
                            <th><@spring.message "page.field.disease.notes"/></th>
                            <@readonlyOrElse readOnlyForm><th><@spring.message "page.actions"/></th></@readonlyOrElse>
                        </tr>
                        </thead>
                        <tbody>
                            <#list MedRecord.diseases>
                                <#items as disease>
                                <tr>
                                    <td>
                                        <div class='${datepicker}'>
                                            <@readonlyOrElse readOnlyForm MedRecord.diseases[disease_index].from?date><@spring.formInput "MedRecord.diseases[${disease_index}].from" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.from')}' required"/><@spring.showErrors "<br/>"/>
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
                                            </@readonlyOrElse>
                                        </div>
                                    </td>
                                    <td>
                                        <div class='${datepicker}'>
                                            <@readonlyOrElse readOnlyForm MedRecord.diseases[disease_index].to?date><@spring.formInput "MedRecord.diseases[${disease_index}].to" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.to')}'"/><@spring.showErrors "<br/>"/>
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i>
                                            </@readonlyOrElse>
                                        </div>
                                    </td>
                                    <td>
                                        <@readonlyOrElse readOnlyForm MedRecord.diseases[disease_index].name><@spring.formInput "MedRecord.diseases[${disease_index}].name" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.name')}' required" "text"/><@spring.showErrors "<br/>"/></@readonlyOrElse>
                                    </td>
                                    <td>
                                        <@readonlyOrElse readOnlyForm MedRecord.diseases[disease_index].notes><@spring.formTextarea "MedRecord.diseases[${disease_index}].notes" "rows='5' cols='80' class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.disease.notes')}'" /><@spring.showErrors "<br/>"/></@readonlyOrElse>
                                    </td>
                                    <@readonlyOrElse readOnlyForm><td>
                                            <a href="#"
                                               onclick="this.disabled=true;post('/medrecord/deleteDisease/${disease_index}', null, 'post', 'MedRecordForm')">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                    </td></@readonlyOrElse>
                                </tr>
                                </#items>
                            </#list>
                        </tbody>
                    </table>
                    <@readonlyOrElse readOnlyForm>
                        <button type="button" class="btn btn-xs"
                                onclick="this.disabled=true;post('/medrecord/addDisease', null, 'post', 'MedRecordForm')">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                        </button>
                    </@readonlyOrElse>
                </div>
            </div> <!-- end of Diseases -->

        <#-- Ultrasounds -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <@spring.message "page.header.patient.ultrasounds"/>
                        <a class="accordion-toggle hidden-print" data-toggle="collapse" href="#ultrasounds"></a>
                    </h3>
                </div>
                <div class="panel-body in" id="ultrasounds">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.analysis.date"/></th>
                            <th><@spring.message "page.field.ultrasound.type"/></th>
                            <th><@spring.message "page.field.analysis.notes"/></th>
                            <@readonlyOrElse readOnlyForm><th><@spring.message "page.actions"/></th></@readonlyOrElse>
                        </tr>
                        </thead>
                        <tbody>
                            <#list MedRecord.ultrasounds>
                                <#items as ultrasound>
                                <tr>
                                    <td>
                                        <div class='${datepicker}'>
                                            <@readonlyOrElse readOnlyForm MedRecord.ultrasounds[ultrasound_index].recordDate!?date><@spring.formInput "MedRecord.ultrasounds[${ultrasound_index}].recordDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.analysis.date')}' required"/><@spring.showErrors "<br/>"/>
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                            </@readonlyOrElse>
                                        </div>
                                    </td>
                                    <td>

                                        <@readonlyOrElse readOnlyForm Ultrasounds[MedRecord.ultrasounds[ultrasound_index].type!'']><@spring.formSingleSelect "MedRecord.ultrasounds[${ultrasound_index}].type", Ultrasounds, "class='form-control chosen-select' placeholder='${springMacroRequestContext.getMessage('page.field.ultrasound.type')}' required">
                                        </@spring.formSingleSelect>
                                        <@spring.showErrors "<br/>"/>
                                        </@readonlyOrElse>
                                    </td>
                                    <td>
                                        <@readonlyOrElse readOnlyForm MedRecord.ultrasounds[ultrasound_index].notes><@spring.formTextarea "MedRecord.ultrasounds[${ultrasound_index}].notes" "rows='5' cols='80' class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.analysis.notes')}'" /><@spring.showErrors "<br/>"/>
                                        </@readonlyOrElse>
                                    </td>
                                    <@readonlyOrElse readOnlyForm><td>
                                            <a href="#"
                                               onclick="this.disabled=true;post('/medrecord/deleteAnalysis/Ultrasound/${ultrasound_index}', null, 'post', 'MedRecordForm')">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                    </td></@readonlyOrElse>
                                </tr>
                                </#items>
                            </#list>

                        </tbody>
                    </table>
                    <@readonlyOrElse readOnlyForm>
                        <button type="button" class="btn btn-xs"
                                onclick="this.disabled=true;post('/medrecord/addAnalysis/Ultrasound', null, 'post', 'MedRecordForm')">
                            <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                        </button>
                    </@readonlyOrElse>
                </div>
            </div> <!-- end of Ultrasounds -->

            <#-- Magnetic Resonance Imaging -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <@spring.message "page.header.patient.mri"/>
                        <a class="accordion-toggle hidden-print" data-toggle="collapse" href="#mri"></a>
                    </h3>
                </div>
                <div class="panel-body in" id="mri">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th><@spring.message "page.field.analysis.date"/></th>
                            <th><@spring.message "page.field.analysis.notes"/></th>
                            <@readonlyOrElse readOnlyForm><th><@spring.message "page.actions"/></th></@readonlyOrElse>
                        </tr>
                        </thead>
                        <tbody>
                        <#list MedRecord.MRIs>
                        <#items as mri>
                        <tr>
                            <td>
                                <div class='${datepicker}'>
                                    <@readonlyOrElse readOnlyForm MedRecord.MRIs[mri_index].recordDate!?date><@spring.formInput "MedRecord.MRIs[${mri_index}].recordDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.analysis.date')}' required"/><@spring.showErrors "<br/>"/>
                                    <span class="input-group-addon">
                                                        <span class="glyphicon glyphicon-calendar"></span>
                                                    </span>
                                    </@readonlyOrElse>
                                </div>
                            </td>
                            <td>
                                <@readonlyOrElse readOnlyForm MedRecord.MRIs[mri_index].notes><@spring.formTextarea "MedRecord.MRIs[${mri_index}].notes" "rows='5' cols='80' class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.analysis.notes')}'" /><@spring.showErrors "<br/>"/></@readonlyOrElse>
                            </td>
                            <@readonlyOrElse readOnlyForm><td>
                                <a href="#"
                                   onclick="this.disabled=true;post('/medrecord/deleteAnalysis/MRI/${mri_index}', null, 'post', 'MedRecordForm')">
                                    <span class="glyphicon glyphicon-trash"></span>
                                </a>
                            </td></@readonlyOrElse>
                        </tr>
                        </#items>
                    </#list>

                    </tbody>
                    </table>
                    <@readonlyOrElse readOnlyForm>
                    <button type="button" class="btn btn-xs"
                            onclick="this.disabled=true;post('/medrecord/addAnalysis/MRI', null, 'post', 'MedRecordForm')">
                        <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
                    </button>
                    </@readonlyOrElse>
                </div>
            </div> <!-- end of Magnetic Resonance Imaging -->

    <#-- CT Scans -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <@spring.message "page.header.patient.ctscan"/>
                <a class="accordion-toggle hidden-print" data-toggle="collapse" href="#ctscan"></a>
            </h3>
        </div>
        <div class="panel-body in" id="ctscan">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th><@spring.message "page.field.analysis.date"/></th>
                    <th><@spring.message "page.field.analysis.notes"/></th>
                    <@readonlyOrElse readOnlyForm><th><@spring.message "page.actions"/></th></@readonlyOrElse>
                </tr>
                </thead>
                <tbody>
                <#list MedRecord.CTScans>
                <#items as ctscan>
                <tr>
                    <td>
                        <div class='${datepicker}'>
                            <@readonlyOrElse readOnlyForm MedRecord.CTScans[ctscan_index].recordDate!?date><@spring.formInput "MedRecord.CTScans[${ctscan_index}].recordDate" "class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.analysis.date')}' required"/><@spring.showErrors "<br/>"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                            </@readonlyOrElse>
                        </div>
                    </td>
                    <td>
                        <@readonlyOrElse readOnlyForm MedRecord.CTScans[ctscan_index].notes><@spring.formTextarea "MedRecord.CTScans[${ctscan_index}].notes" "rows='5' cols='80' class='form-control' placeholder='${springMacroRequestContext.getMessage('page.field.analysis.notes')}'" /><@spring.showErrors "<br/>"/>
                        </@readonlyOrElse>
                    </td>
                    <@readonlyOrElse readOnlyForm><td>
                        <a href="#"
                           onclick="this.disabled=true;post('/medrecord/deleteAnalysis/CTScan/${ctscan_index}', null, 'post', 'MedRecordForm')">
                            <span class="glyphicon glyphicon-trash"></span>
                        </a>
                    </td></@readonlyOrElse>
                </tr>
                </#items>
            </#list>

            </tbody>
            </table>
            <@readonlyOrElse readOnlyForm>
            <button type="button" class="btn btn-xs"
                    onclick="this.disabled=true;post('/medrecord/addAnalysis/CTScan', null, 'post', 'MedRecordForm')">
                <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;<@spring.message "page.button.addNew"/>
            </button>
            </@readonlyOrElse>
    </div>
    </div> <!-- end of Magnetic Resonance Imaging -->
        </div>
    <#-- End of Medical Record -->
    </div>

    <@controls/>

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