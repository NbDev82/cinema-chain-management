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

let currentPriceIncrease = 0;
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
        currentPriceIncrease += productPrice
    } else {
        if (currentQuantity > 0) {
            currentQuantity--;
            currentPrice -= productPrice;
            currentPriceIncrease -= productPrice;
        }
    }

    quantityInput.value = currentQuantity;
    currentPriceTag.innerText = currentPrice.toString();

}
function submitForm() {
    let currentPriceTag = document.getElementById('price');
    let currentPrice = parseInt(currentPriceTag.innerText);
    document.getElementById('selectPrice').value = currentPrice;
    document.getElementById('selectPriceProduct').value = currentPriceIncrease.toString();

    // Lấy tất cả các input có class là 'form-control' trong form
    var quantityInputs = document.querySelectorAll('form input.form-control');

    var data = {};

    // Duyệt qua từng input để lấy thông tin sản phẩm với số lượng khác 0
    quantityInputs.forEach(function(input) {
        var productId = input.name.replace('quantity', '');
        var quantity = parseInt(input.value);

        if (quantity !== 0) {
            data[productId] = quantity; // Lưu thông tin sản phẩm và số lượng vào object data
        }
    });

    // Gán dữ liệu vào input hidden để gửi đi trong form
    document.getElementById('selectPriceProduct').value = JSON.stringify(data);

}

