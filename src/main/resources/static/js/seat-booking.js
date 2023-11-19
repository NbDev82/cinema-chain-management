
function orderSeat(button) {
    if(!button.classList.contains('booked')){
        let currentPriceTag = document.getElementById('price');
        let currentPrice = parseInt(currentPriceTag.innerText);
        if(button.classList.contains('selected')){
            let newPrice = currentPrice - 45000;
            if(newPrice < 0)
                newPrice = currentPrice
            currentPriceTag.innerText = newPrice.toString()
        }else{
            let newPrice = currentPrice + 45000;
            if(newPrice < 0)
                newPrice = currentPrice
            currentPriceTag.innerText = newPrice.toString()
        }
        button.classList.toggle('selected')
        let selectedSeats = $('.seat.selected').map(function () {
            return $(this).attr('value');
        }).get();
        document.getElementById('selectedSeats').value = JSON.stringify(selectedSeats);
    }
}

function submitSelectedSeats() {
    let selectedSeats = $('.seat.selected').map(function () {
        return $(this).attr('value');
    }).get();
    alert(selectedSeats)
    document.getElementById('selectedSeats').value = JSON.stringify(selectedSeats);

    // document.getElementById('seatForm').submit();
    // Sử dụng Ajax để gửi danh sách ghế đã chọn đến controller Spring
    // fetch('/seat/order-seat', {
    //     method: 'POST',
    //     headers: {
    //         'Content-Type': 'application/json'
    //     },
    //     body: JSON.stringify(selectedSeats)
    // })
    //     .catch(error => {
    //         // Xử lý lỗi nếu có
    //         alert('Error:', error);
    //     });
}