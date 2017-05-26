<#import "../fragments/page.master.ftl" as layout/>
<#include "../fragments/page.master.ftl"/>

<#assign layoutOut>

<form class="form-medrecords">
    <@spring.bind "MedRecords"/>

    <div class="page-header">
        <h2>${springMacroRequestContext.getMessage('page.header.medRecords')}</h2>

    </div>
    <div class="row pull-right">
        <a href="/medrecord">
            <button type="button"
                    class="btn btn-primary">${springMacroRequestContext.getMessage('page.button.medRecord.register')}</button>
        </a>
    </div>

    <div class="row">
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
                                <td>${record.createdDate?datetime}</td>
                                <td>
                                    <a href="/medrecord/${(record.id)}">
                                        <button type="button" class="btn btn-xs btn-default">
                                            <span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;View
                                        </button>
                                    </a>
                                    <a href="/medrecord/${(record.id)}">
                                        <button type="button" class="btn btn-xs btn-success">
                                            <span class="glyphicon glyphicon-pencil"></span>&nbsp;&nbsp;Edit
                                        </button>
                                    </a>
                                    <button type="button" class="btn btn-xs btn-danger"
                                            onclick="this.disabled=true;post('/medrecord/${record.id}/delete', {'${_csrf.parameterName}':'${_csrf.token}'})">
                                        <span class="glyphicon glyphicon-trash"></span>&nbsp;&nbsp;Delete
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


     
    <div class="modal fade" id="myModal" role="dialog">
           
        <div class="modal-dialog">

                  <!-- Modal content-->
                 
            <div class="modal-content">
                       
                <div class="modal-header">
                             
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                              <h4 style="color:red;">Remove Medical Record</h4>
                           
                </div>
                       
                <div class="modal-body">
                             
                    <form role="form" method="delete" action="/medrecord">
                                   
                                   
                        <button type="submit" class="btn btn-default btn-success btn-block"><span
                                class="glyphicon glyphicon-off"></span> Login
                        </button>
                                 
                    </form>
                           
                </div>
                       
                <div class="modal-footer">
                              <p>footer</p>
                           
                </div>
                     
            </div>
               
        </div>
         
    </div>
</form>

</#assign>

<@layout.indexmaster  nestedOut="${layoutOut}" titleKey="page.title.medRecords"/>