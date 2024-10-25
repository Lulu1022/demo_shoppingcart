export async function fetchShoppingCartList(userId) {
    const url = `${port}/api/cart/view?userId=${userId}`;
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return await response.json();
    } catch (error) {
        console.error('Fetch error:', error);
        throw error;  // 可以根據需求決定是否需要 rethrow error
    }
}

// 移除商品功能
function removeItem(button) {
    // const cartItem = button.closest('.cart-item');
    // cartItem.remove();
    // calculateTotal();  // 移除商品時重新計算總額
    const productId = button.dataset.productId;
    console.log(productId);
    //TODO: 呼叫 DELETE API 傳入 fakUserId productId
}

export function deleteProduct(userId, productId){
    const url = `${port}/api/cart/remove?userId=${userId}&productId=${productId}`;
    return fetch(url, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        });
}

export function updateProductQuantity(userId, productId, quantity) {
    const url = `${port}/api/cart/update?userId=${userId}&productId=${productId}&quantity=${quantity}`;
    return fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .catch(error => {
            console.error('Error updating product quantity:', error);
            throw error;
        });
}

// 結帳
export function checkout() {
    const url = `http://localhost:8081/TIA103G1/OrderitemServlet`;
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(checkoutItems)  // 將數據轉換為 JSON 字符串發送
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .catch(error => {
            console.error('Error updating product quantity:', error);
            throw error;
        });
}







