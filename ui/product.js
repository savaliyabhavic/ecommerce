Vue.component('productform',{
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


var app = new Vue({
    el:"#app",
    data: {
        title: 'Products'
    },
    methods: {
        
    }
});