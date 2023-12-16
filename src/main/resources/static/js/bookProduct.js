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
    event.preventDefault();
    let currentPriceTag = document.getElementById('price');
    let currentPrice = parseInt(currentPriceTag.innerText);
    document.getElementById('selectPrice').value = currentPrice;
    document.getElementById('selectPriceProduct').value = currentPriceIncrease.toString();

    const productForms = document.querySelectorAll('form[action^="/customer/addtocart/"]');
    let listID = [];

    productForms.forEach(form => {
        let quantityInput = form.querySelector('.form-control.form-control-sm');
        let quantity = parseInt(quantityInput.value);

        if (quantity > 0) {
            let productId = form.getAttribute('action').split('/').pop();
            listID.push({ id: productId, quantity: quantity });

        }
    });

    document.getElementById('listProductBuy').value = JSON.stringify(listID);

    console.log(listID);
    document.getElementById('product_form').submit();
}

