<#import "./fragments/page.master.ftl" as layout/>
<#include "./fragments/page.master.ftl"/>

<#assign layoutOut>

<form class="form-medrecords">
    <@spring.bind "MedRecords"/>

    <div class="row">
        <div class="page-header">
            <h2 class="form-signin-heading">${springMacroRequestContext.getMessage('page.header.medRecords')}</h2>
        </div>
        <div class="pull-right">
            <a href="/medrecord">
                <button type="button" class="btn btn-primary">Register New Medical Record</button>
            </a>
        </div>
    </div>

    <div class="col-md-12">
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Record Number</th>
                    <th>Patient Name</th>
                    <th>Created</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                    <#list MedRecords>
                        <#items as record>
                        <tr>
                            <td>${record?counter}</td>
                            <td>${record.number}</td>
                            <td>${record.patient.fullName}</td>
                            <td>${record.created}</td>
                            <td>
                                <a href="/medrecord/${(record.id)}">
                                    <button type="button" class="btn btn-success"><span
                                            class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Edit
                                    </button>
                                </a>
                            </td>
                        </tr>
                        </#items>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
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
    </div>
</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.medRecords"/>