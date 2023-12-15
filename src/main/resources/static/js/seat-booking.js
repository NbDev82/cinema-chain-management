
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
        document.getElementById('price1').value = currentPriceTag.innerText;

    }
}

function checkSeat(){
    event.preventDefault()
    const selectedSeats = document.querySelectorAll(".selected");
    const numberOfSelectedSeats = selectedSeats.length;
    const condition = numberOfSelectedSeats > 0;
    if (condition) {
        const form = document.getElementById('seatForm');
        alert("success")
        form.submit(); // Gửi form
    }else{
        alert("Please click a available seat before continue!")
    }
}