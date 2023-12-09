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
function orderProduct(button, isAddition) {
    let currentPriceTag = document.getElementById('price');
    let currentPrice = parseInt(currentPriceTag.innerText);

    let productPriceTag = button.closest('.card').querySelector('div:nth-child(3) p');
    let productPrice = parseInt(productPriceTag.innerText.split(' ')[0]);

    if (isAddition) {
        currentPrice += productPrice;
    } else {
        currentPrice -= productPrice;
        if (currentPrice < 0) {
            currentPrice = 0; // Đảm bảo giá không âm
        }
    }

    currentPriceTag.innerText = currentPrice.toString();
    // Các bước còn lại của mã JavaScript của bạn
}

