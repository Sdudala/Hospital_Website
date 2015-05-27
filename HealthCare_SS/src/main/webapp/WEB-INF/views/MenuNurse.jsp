
	<div style="float:left; width:200px; ">
		<ul class="nav nav-pills nav-justified">
			<form:form action="nurse">
                 <input type="Submit" value="Home" class="btn btn-default btn-block" />
            </form:form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form:form action="nurseAppointments" method="post">
                 <input type="Submit" value="Manage Appointments" class="btn btn-default btn-block" />
            </form:form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form:form action="nurseSearch" method="post">
                 <input type="Submit" value="Search Patient" class="btn btn-default btn-block" />
            </form:form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form:form action="changePassword" method="post">
                 <input type="Submit" value="Change Password" class="btn btn-default btn-block" />
            </form:form>
		</ul>
		
		<ul class="nav nav-pills nav-justified">
			<form:form action="logout" method="post">
                 <input type="Submit" value="Logout" class="btn btn-default btn-block" />
            </form:form>
		</ul>
	</div>
