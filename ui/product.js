Vue.component('productform',{
    name: 'productform',
    template: `
    <div class="productform">
    <div class="productFormView">
    <h3>Add {{title}}</h3><br>

    {{title}}
    Name: </br>
        <input v-model="name" class="linespace" name="productName" type="text"><br></br>
    {{title}} Description: </br>
    <textarea v-model="desc" class="extraLinespace" name="productDescription" rows="3" cols="40" wrap="hard"></textarea>
    <br><br>
    Qty: </br>
        <input v-model="qty" number class="linespace" name="productQty" type="number"><br></br>
    Price: </br>
        <input v-model="price" number class="linespace" name="productPrice" type="number"><br></br>
    Category: <br>

    <select v-model="selected" class="linespace" name="category">
        <option disabled value="" checked>Please Select One</option>
        <option value="1">Electronics</option>
        <option value="2">Men</option>
        <option value="3">FootWear</option>
        <option value="4">Furniture</option>
        <option value="5">Study</option>
    </select><br></br>

    <button v-on:click="submit" type="submit">Submit</button>
</div>
    <div>
        Data: <br>
        Name: {{name}}<br>
        Desc: {{desc}}<br>
        Qty: {{qty}}<br>
        Price: {{price}}<br>
        Category: {{selected}}<br>
    </div>
    </div>
    `,
    data(){
        return{
            title:'Product',
            name: 'temp',
            desc: 'temp',
            qty: 0,
            price: 0,
            selected: 0,
        }},
        methods:{
            submit (){
                alert(this.name);
            }
        }
    });

Vue.component('productList',{
        name: 'productList',
        template: `
        <div class="productList">
                <ul class="productListUl">
                    <li v-for="item in productItems">
                        <div class="productItem">
                                <div class="productTileView">
                                        <h4 class="productName">{{item.name}}</h4>
                                </div>
                            
                               <div class="productDetilsView">
                                    <p>{{item.descrption}}</p>
                                    <p>Available Qty: {{item.qty}}</p>
                                    <p>Price: {{item.price}} Rs.</p>
                                    <button>ADD TO CART</button>
                               </div>
                               
                        </div>
                    </li>


                </ul>
        </div>
        `,
        data(){
            return{
                productItems: [
                    {
                        "id": 1,
                        "name": "Dell Vostro",
                        "descrption": "Laptos lalalall balla bla",
                        "qty": 3,
                        "price": 35000,
                        "category": 1,
                        "cid": 1,
                        "cname": "Electronics"
                    },
                    {
                        "id": 2,
                        "name": "Shoool Beg",
                        "descrption": "begs las las bla",
                        "qty": 2,
                        "price": 760,
                        "category": 2,
                        "cid": 2,
                        "cname": "Men"
                    },
                    {
                        "id": 3,
                        "name": "Mi Bend HRX",
                        "descrption": "Branded Fitness",
                        "qty": 10,
                        "price": 1200,
                        "category": 2,
                        "cid": 2,
                        "cname": "Men"
                    },
                    {
                        "id": 5,
                        "name": "Fasttrack Watch",
                        "descrption": "Branded watches",
                        "qty": 4,
                        "price": 2700,
                        "category": 2,
                        "cid": 2,
                        "cname": "Men"
                    },
                    {
                        "id": 6,
                        "name": "WoodLand Shooes",
                        "descrption": "Wond asnjsdbjbckb hsdabc",
                        "qty": 2,
                        "price": 3500,
                        "category": 3,
                        "cid": 3,
                        "cname": "FootWear"
                    },
                    {
                        "id": 8,
                        "name": "Think Pad",
                        "descrption": "laptop brands ",
                        "qty": 4,
                        "price": 30000,
                        "category": 1,
                        "cid": 1,
                        "cname": "Electronics"
                    },
                    {
                        "id": 9,
                        "name": "Redmi Note 5 Pro",
                        "descrption": "Phone lal lal lal",
                        "qty": 10,
                        "price": 15999,
                        "category": 1,
                        "cid": 1,
                        "cname": "Electronics"
                    },
                    {
                        "id": 10,
                        "name": "Oprating System",
                        "descrption": "Book ",
                        "qty": 3,
                        "price": 800,
                        "category": 5,
                        "cid": 5,
                        "cname": "Study"
                    },
                    {
                        "id": 11,
                        "name": "Dell Laptops",
                        "descrption": "bla ba blana",
                        "qty": 2,
                        "price": 20000,
                        "category": 2,
                        "cid": 2,
                        "cname": "Men"
                    }
                ]
            }},
            methods:{
                
            }
        });


var app = new Vue({
    el:"#app",
    data: {
        title: 'Products'
    },
    methods: {
        
    },
    components: {
        'product-List': this.productList,
        'product-form': this.productform
    }
});