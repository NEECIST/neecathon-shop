class Product{
  int id;
  String name;
  int price;
  String description;
  int quantity;
  String image_path;
  Product(this.id,this.name, this.price, this.description, this.quantity,this.image_path);
  String toString() => '$id:  => $quantity  |  value = $price|  description = $description| name= $name|image= $image_path';
  Map toJson() => {'id': id, 'name':name, 'price': price, 'description': description, 'quantity': quantity, 'image_path':image_path};

  factory Product.fromJson(Map<String,dynamic> product){
    Product model_product= new Product(0, "", 0, "", 0, "");

    model_product.id=product["id"];
    model_product.name=product["name"];
    model_product.price=product["price"];
    model_product.description=product["description"];
    model_product.quantity=product["quantity"];
    model_product.image_path=product["image_path"];

    return model_product;
  }
}
