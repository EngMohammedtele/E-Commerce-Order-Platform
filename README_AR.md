# مشروع منصة التجارة الإلكترونية

هذا مشروع Spring Boot REST API يحتوي على 12 كيانًا، DTOs، Validation، Soft Delete، معالجة أخطاء، وعلاقات JPA.

## الفكرة ببساطة

- `Entity`: شكل الجدول في قاعدة البيانات.
- `Repository`: يتحدث مع MySQL بدل كتابة SQL يدويًا.
- `Service`: يحتوي منطق المشروع وقواعده.
- `DTO`: البيانات التي تدخل وتخرج من API.
- `Controller`: يستقبل طلب Postman ويرسل النتيجة.

مسار الطلب هو: Postman -> Controller -> Service -> Repository -> MySQL.

## العلاقات المهمة

- المتجر لديه تصنيفات وعملاء.
- التصنيف يتبع متجرًا ولديه منتجات.
- العميل يتبع متجرًا، وله سلة وعناوين وطلبات وتقييمات.
- السلة تحتوي عناصر، وكل عنصر يشير إلى منتج.
- الطلب يحتوي عناصر طلب، ودفعًا واحدًا، وشحنة واحدة.
- التقييم يربط العميل بالمنتج.

تم تفادي دوران JSON لأن الـControllers تعيد DTO فقط، ولا تعيد Entity مباشرة.

## التشغيل للمبتدئ

1. ثبّت JDK 17 وMySQL وIntelliJ IDEA وPostman.
2. افتح MySQL Workbench ونفّذ: `CREATE DATABASE ecommerce_db;`
3. افتح `src/main/resources/application.properties` وعدّل اسم مستخدم وكلمة مرور MySQL، أو استخدم القيم الافتراضية root/root.
4. افتح مجلد المشروع في IntelliJ وانتظر Maven حتى ينزل المكتبات.
5. شغّل `ECommerceApplication`.
6. عندما ترى `Started ECommerceApplication` افتح Postman واستخدم `http://localhost:8080`.
7. استورد ملف `ECommerce-Platform.postman_collection.json`.

## ترتيب التجربة الصحيح

1. أنشئ Store.
2. أنشئ Category باستخدام `storeId`.
3. أنشئ Product باستخدام `categoryId`.
4. أنشئ Customer باستخدام `storeId`، وستُنشأ سلته تلقائيًا.
5. أضف المنتج للسلة.
6. أنشئ الطلب من السلة.
7. ادفع الطلب؛ النظام ينشئ Shipment تلقائيًا.
8. أنشئ Review بعد شراء المنتج.

## CRUD القياسي

كل مورد يستخدم النمط نفسه، مثل المنتجات:

- `POST /api/products` إنشاء
- `GET /api/products` جلب الكل
- `GET /api/products/{id}` جلب واحد
- `PUT /api/products/{id}` تعديل
- `DELETE /api/products/{id}` حذف ناعم

الموارد: `stores`, `categories`, `products`, `customers`, `addresses`, `carts`, `cart-items`, `orders`, `order-items`, `payments`, `shipments`, `reviews`.

## العمليات الخاصة

- `POST /api/customers/{customerId}/cart/products/{productId}?quantity=2`
- `PUT /api/customers/{customerId}/cart/items/{itemId}?quantity=3`
- `DELETE /api/customers/{customerId}/cart/items/{itemId}`
- `POST /api/orders/place/{customerId}`
- `POST /api/payments`
- `POST /api/reviews`
- `GET /api/products/category/{id}`
- `GET /api/products/below-price?price=20`
- `GET /api/products/low-stock?threshold=5`
- `GET /api/customers/{id}/orders`
- `GET /api/customers/{id}/total-spent`
- `GET /api/reviews/product/{id}/average-rating`
- `GET /api/stats/stores/{id}`
- `GET /api/stats/best-selling-product`

## Soft Delete

الحذف لا يمسح الصف. الخدمة تجعل `isActive=false`، وجميع عمليات القراءة تستخدم `findAllByIsActiveTrue` أو `findByIdAndIsActiveTrue`.

## أسئلة متوقعة من الدكتور

**لماذا DTO؟** حتى لا نعرض Entity وعلاقاته مباشرة، ونحدد البيانات المسموح بها ونتجنب infinite JSON.

**لماذا `@Transactional` عند إنشاء الطلب؟** حتى تنجح كل الخطوات معًا أو يتم التراجع عنها كلها عند حدوث خطأ.

**لماذا نحفظ `unitPrice` في OrderItem؟** لأن سعر المنتج قد يتغير لاحقًا، لكن الطلب يجب أن يحتفظ بسعر وقت الشراء.

**ما الفرق بين `@ManyToOne` و`@OneToMany`؟** منتجات كثيرة تتبع تصنيفًا واحدًا، والتصنيف الواحد يملك منتجات كثيرة.

**كيف يعمل الحذف الناعم؟** بتغيير isActive بدل حذف الصف، ثم استبعاد غير النشط من كل قراءة.
