async function productList(){
    const url = `${port}/product`;
    return fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        });
}

function createProdcutItem(item){

    const{id, name, image, price, currency,details_url} = item;
    const element = document.createElement('div');
    element.setAttribute('data-product-id', id);
    element.classList.add('col');
    element.innerHTML = `<div class="p-3 border bg-light">
                <div>
                  <a href="${details_url}">
                    <img src="./images/basketball.png" alt="pic" class="product-img">
                  </a>
                </div>
                <div class="action-container">
                  <div class="product-details">
                    <a href="details_url">商品名稱 ${name}</a>
                    <div class="product-price">NT$ 999</div>
                  </div>
                  <div class="button-group">
                    <button data-product-id=${id} class="icon-button">
                      <img src="/images/icon/my favorite.png" alt="最愛" class="icon-size">
                    </button>
                    <button data-product-id=${id} class="icon-button">
                      <img src="/images/icon/shopping-car.png" alt="購物車" class="icon-size">
                    </button>
                  </div>
                </div>
                <a href="" class="btn buy-now mt-3 w-100">直接購買</a>
              </div>`;
    const productList = document.getElementById('product-container');
    // element.addEventListener('click', () => {
    //     const roomId = element.dataset.roomId;
    //     console.log(`Room ID: ${roomId}`);
    //     // 在這裡處理點擊後的邏輯，比如打開對應的聊天窗口
    //     openGroupChat()
    // });
    productList.appendChild(element);
}

window.addEventListener('load',()=>{
    getList()
})

// 當頁籤被點擊時，動態加載頁面內容
function loadPage(pageNumber) {
    // 限制頁面範圍
    if (pageNumber < 1 || pageNumber > totalPages) return;

    // 使用模擬的商品數據
    document.getElementById("product-container").innerHTML = productPages[pageNumber];
    currentPage = pageNumber;  // 更新當前頁面
    updatePagination();  // 更新頁籤狀態
}

// 更新「上一頁」和「下一頁」按鈕狀態
function updatePagination() {
    var prevButton = document.getElementById("prevPage");
    var nextButton = document.getElementById("nextPage");

    if (currentPage === 1) {
        prevButton.classList.add("disabled");
    } else {
        prevButton.classList.remove("disabled");
    }

    if (currentPage === totalPages) {
        nextButton.classList.add("disabled");
    } else {
        nextButton.classList.remove("disabled");
    }
}

// 初始化時加載第一頁商品
window.onload = function () {
    loadPage(1);
};

//DEMO
// 取得按鈕元素
const favoriteBtn = document.getElementById('favoriteBtn');
console.log(favoriteBtn)

// 監聽按鈕點擊事件
favoriteBtn.addEventListener('click', function() {

    console.log('heart')
    const heartIcon = favoriteBtn.querySelector('.heart');

    // 切換心型圖示的樣式（空心或實心）
    if (heartIcon.classList.contains('empty')) {
        heartIcon.classList.remove('empty');
        heartIcon.classList.add('filled');
    } else {
        heartIcon.classList.remove('filled');
        heartIcon.classList.add('empty');
    }
});