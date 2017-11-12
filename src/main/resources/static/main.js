
  $(document).ready(function () {
  	$("#login").click(function() {

  		$.ajax({
  			type: "POST",
  			url: action,
  			data: form_data,
  			success: function (response) {
  				if(response == 'success') {

  				}
  				else {

  				}
  			}
  		});
  		return false;
  	});

    $("#register").click(function() {
      $.ajax({
        type:"POST",
        url:action,
        data: form_data,

        success:function (response) {
          if(response == 'success') {

          }
        }

      });
  });
}
