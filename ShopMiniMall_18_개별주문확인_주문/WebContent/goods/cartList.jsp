<%@page import="com.dto.CartDTO"%>
<%@page import="com.dto.GoodsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<table width="90%" cellspacing="0" cellpadding="0" border="0">

	<tr>
		<td height="30">
	</tr>

	<tr>
		<td colspan="5" class="td_default">&nbsp;&nbsp;&nbsp; <font
			size="5"><b>- 장바구니-</b></font> &nbsp;
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>

	<tr>
		<td height="7">
	</tr>

	<tr>
		<td class="td_default" align="center">
		<input type="checkbox" name="allCheck" id="allCheck"> <strong>전체선택</strong></td>
		<td class="td_default" align="center"><strong>주문번호</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>상품정보</strong></td>
		<td class="td_default" align="center"><strong>판매가</strong></td>
		<td class="td_default" align="center" colspan="2"><strong>수량</strong></td>
		<td class="td_default" align="center"><strong>합계</strong></td>
		<td></td>
	</tr>

	<tr>
		<td height="7">
	</tr>
	
	
	
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>


	<form name="myForm">	    
<%
	List<CartDTO> list = (List<CartDTO>)request.getAttribute("cartList"); //CartListServlet에서 "cartList" 키값으로 포워딩한 데이터.
	//System.out.println(list);
	for(int i=0; i<list.size(); i++){
		CartDTO dto = list.get(i);
		int num = dto.getNum();
		String userid = dto.getUserid();
		String gCode = dto.getgCode();
		String gName = dto.getgName();
		int gPrice = dto.getgPrice();
		String gSize = dto.getgSize();
		String gColor = dto.getgColor();
		int gAmount = dto.getgAmount();
		String gImage = dto.getgImage();
%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
	$(function(){
		
		//삭제버튼
		$(".delBtn").on("click", function() {
			var num = $(this).attr("data-xxx"); //주문번호
			//console.log(num);
			location.href="CartDelServlet?num="+num;
		});
		
		$(".updateBtn").on("click", function() {
			var num = $(this).attr("data-xxx"); //주문번호
			var gAmount = $("#cartAmount"+num).val(); //주문번호로 수량
			var gPrice = $(this).attr("data-price"); //가격
			var sum = $("#sum"+num).text();
			//console.log(num, gPrice);
			//console.log(gAmount);
			
			$.ajax({
				url: "CartUpdateServlet",
				type: "get",
				dataType: "text",
				data:{
					num: num,
					gAmount: gAmount
				},
				success: function (data,status,xhr) {
					var sum = gAmount*gPrice;
					$("#sum"+num).text(sum);
					//console.log("success");
				},
				error: function(xhr, status, error) {
					console.log("error");
				}
			
			});//ajax end
		
		});//update
		
		$("#allCheck").on("click", function() {
			var result = this.checked;
			$(".check").each(function(idx, data) {
				data.checked = result;
			})
		
		});
		
		$("#delAllCart").on("click", function() {
			var num = [];
			$(".check:checked").each(function(idx, ele) {
				num[idx] = $(this).val();
			});
			console.log(num);
			location.href="CartDelAllServlet?data="+num;
			//CartDelAllServlet?data=10,8
		});
		
		$("#delAllCart2").on("click", function() {
			$("form").attr("action", "CartDelAllServlet2");
			$("form").submit(); //trigger
		});
		
		//주문버튼
		$(".orderBtn").on("click", function() {
			var num = $(this).attr("data-xxx");
			location.href = "CartOrderConfirmServlet?num="+num;
		});
		
		
		
	});//ready
	
	

</script>
   
	    
	  		 <input type="text" name="num81" value="81" id="num81">
	<!-- hidden부분 설정부분 	
	<input type="text" name="gImage81" value="bottom1" id="gImage81">
		 <input type="text" name="gName81" value="제나 레이스 스커트" id="gName81">
		  <input type="text" name="gSize81" value="L" id="gSize81">
		   <input type="text" name="gColor81" value="navy" id="gColor81"> 
		   <input type="text" name="gPrice81" value="9800" id="gPrice81"> -->

		<tr>
			<td class="td_default" width="80">
			<!-- checkbox는 체크된 값만 서블릿으로 넘어간다. 따라서 value에 삭제할 num값을 설정한다. -->
			<input type="checkbox"
				name="check" id="check81" class="check" value="<%= num %>"></td>
			<td class="td_default" width="80" ><%= num %></td>
			<td class="td_default" width="80">
				<img src="images/items/<%= gImage %>.gif" border="0" align="center" width="80" /></td> <!-- 이미지는 폴더 잘 찾아주기 -->
			<td class="td_default" width="300" style='padding-left: 30px'>
			<%= gName %>
				<br> <font size="2" color="#665b5f">[옵션 : 사이즈(<%= gSize %>)
					, 색상(<%= gColor %>)]
			</font></td>
			<td class="td_default" align="center" width="110">
			<%= gPrice %>
			</td>
			<td class="td_default" align="center" width="90">
			<input class="input_default" type="text" name="cartAmount"
				id="cartAmount<%= num %>" style="text-align: right" maxlength="3" 
				size="2" value="<%= gAmount %>"></input></td>
			<td><input type="button" value="수정" class="updateBtn" data-xxx="<%= num %>" data-price="<%= gPrice%>"></td>
			<td class="td_default" align="center" width="80"
				style='padding-left: 5px'><span id="sum<%= num %>">
				<%= gPrice*gAmount %>
				</span></td>
			<td><input type="button" value="주문" class="orderBtn" data-xxx="<%= num %>"></td>
			<td class="td_default" align="center" width="30"
				style='padding-left: 10px'>
				<input type="button" value="삭제" class="delBtn" data-xxx="<%= num %>" ></td>
			<td height="10"></td>
		</tr>

<%
	} 
%>

	</form>
	<tr>
		<td colspan="10">
			<hr size="1" color="CCCCCC">
		</td>
	</tr>
	<tr>
		<td height="30">
	</tr>

	<tr>
		<td align="center" colspan="5">
			<a class="a_black" href="javascript:orderAllConfirm(myForm)"> 전체 주문하기 </a>&nbsp;&nbsp;&nbsp;&nbsp; 
			<a class="a_black" href="#" id="delAllCart"> 전체 삭제하기1 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="#" id="delAllCart2"> 전체 삭제하기2 </a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a_black" href="main"> 계속 쇼핑하기 </a>&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td height="20">
	</tr>

</table>
    