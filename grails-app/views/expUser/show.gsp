<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="User Expense Sheet" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        
                    <div class="card">
                            
                        <div class="card-header">
                            <h4><i class="fa fa-file-text-o"></i> Expense Sheet: ${userInstance.userName}</h4>
                            <span style="color:#abcdef">Balance: 
                                <g:formatNumber number="${userInstance.transactions.amountZAR.sum()}" currencyCode="ZAR" type="currency"  format="###,##0" />
                            </span>
                            <form name=deleteForm action="/expUser/delete/${userInstance.id}" method=POST style="display:none"></form>
                            <button class="btn btn-default" style="float:right;font-size:60%" onclick="javascript:confirm('User and related sheets will be deleted. Continue?')? document.deleteForm.submit():null">
                                <i class="fa fa-unlink"></i> Delete
                            </button>
                        </div>
                        <div class="card-body">

                            <h4 align="left">Your transactions</h4>
                            <p>&nbsp;</p>

                            <table>
                                <tr style="background: #cccccc;">
                                    <td>Date</td>
                                    <td>Type</td>
                                    <td>Reference</td>
                                    <td style="text-align:right">ZAR<BR>Amount</td>
                                    <td style="text-align:right">USD<BR>Amount</td>
                                    <td style="text-align:right">ZAR<BR>Running Balance</td>
                                    <td>&nbsp;</td>
                                </tr>    
                                <g:each var="t" in="${userInstance.transactions.sort{a,b->a.transactionDate.compareTo(b.transactionDate)}}">
                                    <tr ${t.amountZAR < 0 ? "style=background-color:#FFD7C4" : ""}>
                                        <td>${t.transactionDate}</td>
                                        <td>${t.amountZAR < 0 ? "Expense" : "Deposit"}</td>
                                        <td>${t.transactionRef}</td>
                                        <td style="text-align:right"><g:formatNumber number="${t.amountZAR}" currencyCode="ZAR" type="currency"  format="###,##0" /></td>
                                        <td style="text-align:right"><g:formatNumber number="${t.amountUSD}" type="currency" format="###,##0" /></td>
                                        <td style="text-align:right"><g:formatNumber number="${t.runningBalance}" currencyCode="ZAR" type="currency"  format="###,##0" /></td>
                                        <td style="text-align:center"><button class="btn btn-default" style="padding:4px" onclick="javascript:document.location='/transaction/edit/${t.id}'"><i class="fa fa-edit"></i> Edit</button>
                                    </tr>
                                </g:each>
                            </table>
                            
                            <p>&nbsp;</p>
                            <button class="btn btn-default" onclick="javascript:document.location='/'"><i class="fa fa-home"></i> Home</button>
                            <button class="btn btn-primary" onclick="javascript:document.location='/transaction/create/${userInstance.id}?abs=1'"><i class="fa fa-angle-double-up"></i> Add Funds</button>
                            <button class="btn btn-secondary" onclick="javascript:document.location='/transaction/create/${userInstance.id}?abs=0'"><i class="fa fa-angle-double-down"></i> Add Expense</button>
                            <button class="btn btn-default" onclick="javascript:document.location='/transaction/exportCSV/${userInstance.id}'"><i class="fa fa-file-excel-o"></i> Export CSV</button>
                        </div>
                    </div>
            

    </body>
</html>
