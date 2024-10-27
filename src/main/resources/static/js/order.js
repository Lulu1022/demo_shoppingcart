import {fetchOrderItem,} from "./orderApi";

// import {fetchShoppingCartList, updateProductQuantity, deleteProduct,checkout} from "./cartApi.js";
// 範例
import {fetchShoppingCartList} from "./cartApi";

// async function getShoppingCartList(userId) {
//     try {
//         const data = await fetchShoppingCartList(userId);
//
//         document.getElementById('allSchedule').innerHTML='';
//
//
//         data.forEach(item => {
//             console.log(item);
//             const{productList,supplierId,supplierName,quantity} = item;
//
//             const shop = document.createElement('div');
//             shop.setAttribute('id', `data-supplier-${supplierId}`);
//             document.getElementById('allSchedule').append(shop);
//
//             const supplierDiv = document.createElement('div');
//             supplierDiv.className = 'schedule';
//             supplierDiv.innerHTML=`<input data-supplier-id="${supplierId}" type="checkbox" id="store-a-checkbox" onclick="toggleStoreItems(${supplierId}, this)">
//                     <label for="store-a-checkbox" class="mall-label">${supplierName}</label> 預計取貨時間：2024/11/25`;
//             shop.append(supplierDiv);
//
//             productList.forEach(product =>{
//                 const{productId,productName,productSpec,picture,price,quantity} = product;
//
//                 let totalPrice = quantity * price;
//                 const element = document.createElement('div');
//                 element.className = 'cart-item row store-b';
//                 element.innerHTML = `
//                 <div class="col-md-1">
//                     <input data-product-id=${productId} data-supplier-id="${supplierId}" type="checkbox" class="store-b-checkbox item-checkbox" onclick="calculateTotal()">
//                 </div>
//                 <div class="col-md-2">
//                     <img src="https://via.placeholder.com/100" alt="商品100" class="img-fluid">
//                 </div>
//                 <div class="col-md-5">
//                     <h5>${productName}</h5>
//                     <h6>${productSpec}</h6>
//                 </div>
//                 <div data-price="${price}" class="col-md-2">${price}</div>
//                 <div class="col-md-2 quantity-control">
//                     <button class="btn btn-outline-secondary" data-product-id=${productId}  onclick="decreaseQuantity(this)">-</button>
//                     <span class="quantity">${quantity}</span>
//                     <button class="btn btn-outline-secondary" data-product-id=${productId} onclick="increaseQuantity(this)">+</button>
//                     <button class="remove-btn" data-product-id=${productId} onclick="removeItem(this)">移除</button>
//                 </div>
// `;
//
//                 shop.append(element);
//             })
//         });
//     } catch (error) {
//         console.error('There was a problem with the fetch operation:', error);
//     }
// }

