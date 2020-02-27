<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expUser.label', default: 'ExpUser')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        
                    <div class="card">
                            
                        <div class="card-header">
                                <h4><i class="fa fa-money"></i> Welcome ${userInstance.userName}</h4>
                                <span style="color:#abcdef">Balance: 
                                <g:formatNumber number="${userInstance.transactions.amountZAR.sum()}" currencyCode="ZAR" type="currency"  format="###,##0" />
                            </span>
                        </div>
                        <div class="card-body">

                            <h4 align="left">Your transactions</h4>
                            <p>&nbsp;</p>

                            <table>
                                <tr style="background: #cccccc;">
                                    <td>Date</td>
                                    <td>Type</td>
                                    <td>Reference</td>
                                    <td style="text-align:right">ZAR Amount</td>
                                    <td style="text-align:right">USD Amount</td>
                                    <td style="text-align:right">ZAR Running Balance</td>
                                </tr>    
                                <g:each var="t" in="${userInstance.transactions.sort{a,b->a.transactionDate.compareTo(b.transactionDate)}}">
                                    <tr ${t.amountZAR < 0 ? "style=background-color:#FFD7C4" : ""}>
                                        <td>${t.transactionDate}</td>
                                        <td>${t.amountZAR < 0 ? "Expense" : "Deposit"}</td>
                                        <td>${t.transactionRef}</td>
                                        <td style="text-align:right"><g:formatNumber number="${t.amountZAR}" currencyCode="ZAR" type="currency"  format="###,##0" /></td>
                                        <td style="text-align:right"><g:formatNumber number="${t.amountUSD}" type="currency" format="###,##0" /></td>
                                        <td style="text-align:right"><g:formatNumber number="${t.runningBalance}" currencyCode="ZAR" type="currency"  format="###,##0" /></td>
                                    </tr>
                                </g:each>
                            </table>
                            
                            <p>&nbsp;</p>
                            <button class="btn btn-primary" onclick="javascript:document.location='/transaction/create/${userInstance.id}?abs=1'"><i class="fa fa-angle-double-up"></i> Add Funds</button>
                            <button class="btn btn-secondary" onclick="javascript:document.location='/transaction/create/${userInstance.id}?abs=0'"><i class="fa fa-angle-double-down"></i> Add Expense</button>
                            <button class="btn btn-default"><i class="fa fa-file-excel-o"></i> Export CSV</button>
                        </div>
                    </div>
            

    </body>
</html>
