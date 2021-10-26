$(document).ready(function(){
    toastr.options = {
      "closeButton": false,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-bottom-right",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "5000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    };

    $("input[name='loveradio']").click(function() {
        var loveradio = $("input[name='loveradio']:checked").val();
        var idProduct = $("#idproduct").html();
        $.ajax({
            async: true,
            type: "post",
            url: "/vote/product",
            dataType: 'json',
            data: {
                voteValue : loveradio,
                idProduct : idProduct
            },
            success: function(response) {
                if(response.status == "Done") {
                    $("#ilovequantity").html(response.data.loveQuantity);
                    $("#ilikequantity").html(response.data.likeQuantity);
                    $("#idislikequantity").html(response.data.disLikeQuantity);
                    toastr.success("Dziękujemy za twój głos.", "Zagłosowano!");
                }
                if(response.status == "not-login") {
                    toastr.warning("Błąd", "Aby zagłosować musisz być zalogowany!");
                }
            },
            error: function(response) {
                toastr.error("Błąd", "Wystąpił nieznany błąd");
            },
        });
    })

    $('input[name="have"], input[name="season"], input[name="daynight"]').change(
        function () {
            var idProduct = $("#idproduct").html();
            if ($(this).is(':checked')) {
                var voteName = $(this).val();
                var voteValue = 1;
            } else if ($(this).not(':checked')) {
    		    var voteName = $(this).val();
    			var voteValue = 0;
    		}
    		$.ajax ({
    		url: "/vote/productdetail",
    		type: "POST",
    		data: {
    		    voteName: voteName,
    		    voteValue: voteValue,
    		    idProduct: idProduct
    		},
    		dataType: 'json',
    		success: function(response) {
    		    if(response.status == "Done") {
                    if (voteName == 'ihave') $("#ihaveQuantity").html(response.data.quantity)
                    if (voteName == 'iwant') $("#iwantQuantity").html(response.data.quantity)
                    if (voteName == 'ihad') $("#ihadQuantity").html(response.data.quantity)
                    if (voteName == 'winter') $("#winterQuantity").html(response.data.quantity)
                    if (voteName == 'spring') $("#springQuantity").html(response.data.quantity)
                    if (voteName == 'summer') $("#summerQuantity").html(response.data.quantity)
                    if (voteName == 'autumn') $("#autumnQuantity").html(response.data.quantity)
                    if (voteName == 'day') $("#dayQuantity").html(response.data.quantity)
                    if (voteName == 'night') $("#nightQuantity").html(response.data.quantity)
                    toastr.success("Dziękujemy za twój głos.", "Zagłosowano!");
                }
                if(response.status == "not-login") {
                    toastr.warning("Błąd", "Aby zagłosować musisz być zalogowany!");
                }
    		},
    		error: function() {
    			toastr.error("Błąd", "Wystąpił nieznany błąd");
    		},
    	    });
    	}
    );

    $('input.form-user-note-vote').change(function() {
        var idProduct = $("#idproduct").html();
        if ($(this).is(':checked')) {
            var valueNote = $(this).val();
            var idNote = $(this).attr('id');
        }
        console.log(valueNote);
        console.log(idNote);
        console.log(idProduct);
        $.ajax ({
            url: "/vote/votenote",
            type: "POST",
            data: {
                idNote: idNote,
                valueNote: valueNote,
                idProduct: idProduct
            },
            dataType: 'json',
            success: function(response) {

                if(response.status == "Done") {
                    var idelement = "#note" + response.data.idNote;
                    $(idelement).html(response.data.value)
                    toastr.success("Dziękujemy za twój głos.", "Zagłosowano!");
                }
                if(response.status == "not-login") {
                    toastr.warning("Błąd", "Aby zagłosować musisz być zalogowany!");
                }
            },
            error: function() {
                toastr.error("Błąd", "Wystąpił nieznany błąd");
            },
        });
    })

    var KTIONRangeSlider = function () {

    	// Private functions
    	var demos = function () {
    		// basic demo
    		$('#kt_slider_1').ionRangeSlider({
    		    grid: true,
                min: 1,
                max: 5,
                from: 3,
                skin: 'big'
    		});
    	}

    	return {
    		// public functions
    		init: function() {
    			demos();
    		}
    	};
    }();

    KTIONRangeSlider.init();

});
