var menu_ts = document.querySelectorAll('.category_list-item.menu_ts');

menu_ts.forEach((menuItem) => {
    var dentail = menuItem.querySelector('.list-dentail');
    var round_round = menuItem.querySelector('.round-round');

    menuItem.addEventListener('click', () => {
        var all_none = document.querySelectorAll('.list-dentail');
        var transform_none = document.querySelectorAll('.round-round')
        for (var i = 0; i < all_none.length; i++) {
            if (all_none[i] !== dentail) {
                all_none[i].classList.remove('toggle-auto');
                transform_none[i].classList.remove('transform-deg');
            }
        }
        
        dentail.classList.toggle('toggle-auto');
        round_round.classList.toggle('transform-deg');
    });
});


var close_dentail = document.querySelector('.close_dentail')
close_dentail.addEventListener('click', () =>{
    var center_product = document.querySelector('.center_product')
    center_product.style.display = 'none'
})

// format vnd
function formatCurrency(amount) {
    const formattedAmount = amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return formattedAmount + ' VNĐ';
}



// get atributes from the product element and add in center
 function showProductDetails(name, priceM,priceL, image,codeProduct){

			var productShow = document.getElementById("product-details");
		    var quantityShow = document.getElementById('quantity_product-show');
		    var priceTotal = document.querySelector('.add_product-click.width_total');
		    var codeProductTake = document.getElementById('code-product-take')
		    var quantityTake = document.getElementById('code-quantity-take')
		    var totalPriceTake = document.getElementById('code-totalPrice-take')
		    var priceToppingTake = document.getElementById('code-price-topping-take')
		    priceToppingTake.value = 0;
		    codeProductTake.value = codeProduct
		    var quantity = 1;
		    var pricePresent = 0;
		    var priceTopping = 0;

            var productName = name;
            var productPriceL = priceL; 
            var productPriceM = priceM; 
            var productImg = image;
            
            var nameProduct = document.getElementById('name_center-sp');
            var priceProduct = document.getElementById('price_center-sp');
            var imgProduct = document.getElementById('img_center-sp');
			
		    var radioSize = document.getElementsByName('codeSizeTake');
		    var checkboxes = document.querySelectorAll('.option-size.takeTopping');
		    var sugerAll = document.getElementsByName('type-suger')
		    
		    function resetState() {
	            quantity = 1;
	            priceTopping = 0;
				pricePresent = 0
				priceToppingTake.value = 0;
				
	            quantityShow.value = quantity;
	            quantityTake.value = quantity
	            checkboxes.forEach(function(checkbox) {
	                checkbox.checked = false;
	           		 });
	           	sugerAll.forEach((suger)=>{
					suger.checked = false;
				})
	           		 radioSize[0].checked = true
	           		 sugerAll[1].checked = true
	           	for(var i = 0 ;i < radioSize.length;i++){
						radioSize[i].parentNode.style.display = "flex"
				}
	       	}
	       		 

	        resetState();
		    radioSize[0].value = parseFloat(productPriceM)
		    radioSize[1].value = parseFloat(productPriceL)
		    for(var i = 0 ;i < radioSize.length;i++){
				if(radioSize[i].value == 0){
					radioSize[i].parentNode.style.display = "none"
				}
			}
		    nameProduct.textContent = productName;
            imgProduct.src = productImg;
            priceProduct.textContent = formatCurrency(parseFloat(productPriceM))
            priceTotal.value = parseFloat(productPriceM)
            totalPriceTake.value = parseFloat(productPriceM)
            pricePresent = parseFloat(productPriceM)
            quantityShow.value = quantity;
              quantityTake.value = quantity
            productShow.style.display = 'flex';

            
            function updatePrice() {
                for (var i = 0; i < radioSize.length; i++) {
                    if (radioSize[i].checked) {
                        var selectedValue = parseFloat(radioSize[i].value);
                        priceProduct.textContent = formatCurrency(selectedValue);
                        priceTotal.value = (selectedValue + priceTopping) * quantity; // Cập nhật giá sản phẩm tổng cộng
                        totalPriceTake.value = (selectedValue + priceTopping) * quantity;
                        pricePresent = selectedValue;
                        break;
                    }
                }
            }

            updatePrice();
			
		    for (var i = 0; i < radioSize.length; i++) {
		        radioSize[i].addEventListener('change', updatePrice);
		    }

			
			checkboxes.forEach(function(checkbox) {
			    checkbox.addEventListener('click', function() {
			        if (checkbox.checked) {
			            priceTopping += parseInt(checkbox.value);
			        } else {
			            priceTopping -= parseInt(checkbox.value);
			        }
			        totalPriceTake.value = (pricePresent + priceTopping) * quantity;
			        priceTotal.value = (pricePresent + priceTopping) * quantity; // Cập nhật giá sản phẩm tổng cộng
			        priceToppingTake.value = priceTopping;
			        
			    });
			});

			var highProduct = document.querySelector('.quantity_product-plus')
			highProduct.addEventListener('click', function() {
			    quantity++;
			    quantityShow.value = quantity;
			    quantityTake.value = quantity
			  	 totalPriceTake.value = (pricePresent + priceTopping) * quantity;
			    priceTotal.value = (pricePresent + priceTopping) * quantity; // Cập nhật giá sản phẩm tổng cộng
			});


			var lowProduct = document.querySelector('.quantity_product-mul')
			lowProduct.addEventListener('click', function() {
			    if (quantity > 1) {
			        quantity--;
			        quantityShow.value = quantity;
			         quantityTake.value = quantity
			           totalPriceTake.value = (pricePresent + priceTopping) * quantity;
			        priceTotal.value = (pricePresent + priceTopping) * quantity; // Cập nhật giá sản phẩm tổng cộng
			    }else{
					 quantityShow.value = 1;
					 quantityTake.value = 1;
				}
			});
}

function updateHiddenInput(checkbox, nameTopping) {
    var hiddenInput = document.getElementById('code-allTopping-take');
    var allToppings = hiddenInput.value.split(',');

    if (checkbox.checked) {
        allToppings.push(nameTopping);
    } else {
        var index = allToppings.indexOf(nameTopping);
        if (index !== -1) {
            allToppings.splice(index, 1);
        }
    }

    hiddenInput.value = allToppings.join(',');
}




