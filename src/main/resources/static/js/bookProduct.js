// function orderProduct(button, isAddition) {
//     let currentPriceTag = document.getElementById('price');
//     let currentPrice = parseInt(currentPriceTag.innerText);
//
//     let productPriceTag = button.closest('.card').querySelector('div:nth-child(3) p');
//     let productPrice = parseInt(productPriceTag.innerText.split(' ')[0]);
//
//     if (isAddition) {
//         currentPrice += 1000;
//     } else {
//         currentPrice -= 1000;
//         if (currentPrice < 0) {
//             currentPrice = 0; // Đảm bảo giá không âm
//         }
//     }
//
//     currentPriceTag.innerText = currentPrice.toString();
//     // Các bước còn lại của mã JavaScript của bạn
// }
function updateQuantityAndTotalPrice(button, isAddition) {
    let quantityInput = button.parentNode.querySelector('input[type="number"]');
    let currentQuantity = parseInt(quantityInput.value);

    let priceElement = button.closest('.card').querySelector('div:nth-child(3) p');
    let productPrice = parseInt(priceElement.innerText.split(' ')[0]);

    let currentPriceTag = document.getElementById('price');
    let currentPrice = parseInt(currentPriceTag.innerText);

    if (isAddition) {
        currentQuantity++;
        currentPrice += productPrice;
    } else {
        if (currentQuantity > 0) {
            currentQuantity--;
            currentPrice -= productPrice;
        }
    }

    quantityInput.value = currentQuantity;
    currentPriceTag.innerText = currentPrice.toString();
    document.getElementById('selectPrice').value = currentPriceTag.innerText;

}
function submitForm() {
    let currentPriceTag = document.getElementById('price');
    let currentPrice = parseInt(currentPriceTag.innerText);

    document.getElementById('selectPrice').value = currentPrice;
}

