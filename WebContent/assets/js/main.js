$(document).ready(function() {
	if ($('#achats').attr('checked') != 'checked') {
		$('#openBids, #myBids, #winBids').attr('disabled', 'disabled');
	}
	if ($('#sales').attr('checked') != 'checked') {
		$('#openSales, #pendingSales, #endSales').attr('disabled', 'disabled');
	}
	
	$('#achats').click(function() {
		$('#openSales, #pendingSales, #endSales').attr('disabled', 'disabled');
		$('#openBids, #myBids, #winBids').removeAttr('disabled');
	});
	
	$('#sales').click(function() {
		$('#openBids, #myBids, #winBids').attr('disabled', 'disabled');
		$('#openSales, #pendingSales, #endSales').removeAttr('disabled');
	});
	
	$('#achats').on('click', function () {
		$('#selectedAchatsBlock').addClass('blockColor');
		$('#selectedSalesBlock').removeClass('blockColor');
	});
	
	$('#sales').on('click', function () {
		$('#selectedSalesBlock').addClass('blockColor');
		$('#selectedAchatsBlock').removeClass('blockColor');
	});
	

});