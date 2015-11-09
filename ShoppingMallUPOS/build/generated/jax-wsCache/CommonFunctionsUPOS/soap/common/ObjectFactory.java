
package soap.common;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.common package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetShopItemByItemIdResponse_QNAME = new QName("http://common.soap/", "getShopItemByItemIdResponse");
    private final static QName _CreateShopOrder_QNAME = new QName("http://common.soap/", "createShopOrder");
    private final static QName _CreateDetailOrderResponse_QNAME = new QName("http://common.soap/", "createDetailOrderResponse");
    private final static QName _GetTablesByRestaurantId_QNAME = new QName("http://common.soap/", "getTablesByRestaurantId");
    private final static QName _AddShoppingMallItem_QNAME = new QName("http://common.soap/", "addShoppingMallItem");
    private final static QName _DeleteShopItemByItemId_QNAME = new QName("http://common.soap/", "deleteShopItemByItemId");
    private final static QName _GetMemberAccountByNFCCardNumResponse_QNAME = new QName("http://common.soap/", "getMemberAccountByNFCCardNumResponse");
    private final static QName _GetShoppingMallItemsByShopId_QNAME = new QName("http://common.soap/", "getShoppingMallItemsByShopId");
    private final static QName _GetShoppingMallItemsByShopIdResponse_QNAME = new QName("http://common.soap/", "getShoppingMallItemsByShopIdResponse");
    private final static QName _AddRestaurantTable_QNAME = new QName("http://common.soap/", "addRestaurantTable");
    private final static QName _AddRestaurantTableResponse_QNAME = new QName("http://common.soap/", "addRestaurantTableResponse");
    private final static QName _GetShopByIdResponse_QNAME = new QName("http://common.soap/", "getShopByIdResponse");
    private final static QName _CreateShopOrderResponse_QNAME = new QName("http://common.soap/", "createShopOrderResponse");
    private final static QName _GetMemberAccountByNFCCardNum_QNAME = new QName("http://common.soap/", "getMemberAccountByNFCCardNum");
    private final static QName _GetShopItemByItemId_QNAME = new QName("http://common.soap/", "getShopItemByItemId");
    private final static QName _GetShopById_QNAME = new QName("http://common.soap/", "getShopById");
    private final static QName _GetTablesByRestaurantIdResponse_QNAME = new QName("http://common.soap/", "getTablesByRestaurantIdResponse");
    private final static QName _CreateDetailOrder_QNAME = new QName("http://common.soap/", "createDetailOrder");
    private final static QName _GetShopItemByBarcodeResponse_QNAME = new QName("http://common.soap/", "getShopItemByBarcodeResponse");
    private final static QName _DeleteShopItemByItemIdResponse_QNAME = new QName("http://common.soap/", "deleteShopItemByItemIdResponse");
    private final static QName _GetShopItemByBarcode_QNAME = new QName("http://common.soap/", "getShopItemByBarcode");
    private final static QName _AddShoppingMallItemResponse_QNAME = new QName("http://common.soap/", "addShoppingMallItemResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.common
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetShoppingMallItemsByShopIdResponse }
     * 
     */
    public GetShoppingMallItemsByShopIdResponse createGetShoppingMallItemsByShopIdResponse() {
        return new GetShoppingMallItemsByShopIdResponse();
    }

    /**
     * Create an instance of {@link AddRestaurantTable }
     * 
     */
    public AddRestaurantTable createAddRestaurantTable() {
        return new AddRestaurantTable();
    }

    /**
     * Create an instance of {@link AddRestaurantTableResponse }
     * 
     */
    public AddRestaurantTableResponse createAddRestaurantTableResponse() {
        return new AddRestaurantTableResponse();
    }

    /**
     * Create an instance of {@link GetShopItemByItemId }
     * 
     */
    public GetShopItemByItemId createGetShopItemByItemId() {
        return new GetShopItemByItemId();
    }

    /**
     * Create an instance of {@link GetShopById }
     * 
     */
    public GetShopById createGetShopById() {
        return new GetShopById();
    }

    /**
     * Create an instance of {@link GetShopByIdResponse }
     * 
     */
    public GetShopByIdResponse createGetShopByIdResponse() {
        return new GetShopByIdResponse();
    }

    /**
     * Create an instance of {@link CreateShopOrderResponse }
     * 
     */
    public CreateShopOrderResponse createCreateShopOrderResponse() {
        return new CreateShopOrderResponse();
    }

    /**
     * Create an instance of {@link GetMemberAccountByNFCCardNum }
     * 
     */
    public GetMemberAccountByNFCCardNum createGetMemberAccountByNFCCardNum() {
        return new GetMemberAccountByNFCCardNum();
    }

    /**
     * Create an instance of {@link GetTablesByRestaurantIdResponse }
     * 
     */
    public GetTablesByRestaurantIdResponse createGetTablesByRestaurantIdResponse() {
        return new GetTablesByRestaurantIdResponse();
    }

    /**
     * Create an instance of {@link CreateDetailOrderResponse }
     * 
     */
    public CreateDetailOrderResponse createCreateDetailOrderResponse() {
        return new CreateDetailOrderResponse();
    }

    /**
     * Create an instance of {@link GetShopItemByItemIdResponse }
     * 
     */
    public GetShopItemByItemIdResponse createGetShopItemByItemIdResponse() {
        return new GetShopItemByItemIdResponse();
    }

    /**
     * Create an instance of {@link CreateShopOrder }
     * 
     */
    public CreateShopOrder createCreateShopOrder() {
        return new CreateShopOrder();
    }

    /**
     * Create an instance of {@link AddShoppingMallItem }
     * 
     */
    public AddShoppingMallItem createAddShoppingMallItem() {
        return new AddShoppingMallItem();
    }

    /**
     * Create an instance of {@link GetTablesByRestaurantId }
     * 
     */
    public GetTablesByRestaurantId createGetTablesByRestaurantId() {
        return new GetTablesByRestaurantId();
    }

    /**
     * Create an instance of {@link DeleteShopItemByItemId }
     * 
     */
    public DeleteShopItemByItemId createDeleteShopItemByItemId() {
        return new DeleteShopItemByItemId();
    }

    /**
     * Create an instance of {@link GetShoppingMallItemsByShopId }
     * 
     */
    public GetShoppingMallItemsByShopId createGetShoppingMallItemsByShopId() {
        return new GetShoppingMallItemsByShopId();
    }

    /**
     * Create an instance of {@link GetMemberAccountByNFCCardNumResponse }
     * 
     */
    public GetMemberAccountByNFCCardNumResponse createGetMemberAccountByNFCCardNumResponse() {
        return new GetMemberAccountByNFCCardNumResponse();
    }

    /**
     * Create an instance of {@link GetShopItemByBarcode }
     * 
     */
    public GetShopItemByBarcode createGetShopItemByBarcode() {
        return new GetShopItemByBarcode();
    }

    /**
     * Create an instance of {@link AddShoppingMallItemResponse }
     * 
     */
    public AddShoppingMallItemResponse createAddShoppingMallItemResponse() {
        return new AddShoppingMallItemResponse();
    }

    /**
     * Create an instance of {@link CreateDetailOrder }
     * 
     */
    public CreateDetailOrder createCreateDetailOrder() {
        return new CreateDetailOrder();
    }

    /**
     * Create an instance of {@link GetShopItemByBarcodeResponse }
     * 
     */
    public GetShopItemByBarcodeResponse createGetShopItemByBarcodeResponse() {
        return new GetShopItemByBarcodeResponse();
    }

    /**
     * Create an instance of {@link DeleteShopItemByItemIdResponse }
     * 
     */
    public DeleteShopItemByItemIdResponse createDeleteShopItemByItemIdResponse() {
        return new DeleteShopItemByItemIdResponse();
    }

    /**
     * Create an instance of {@link Shop }
     * 
     */
    public Shop createShop() {
        return new Shop();
    }

    /**
     * Create an instance of {@link MemberPayment }
     * 
     */
    public MemberPayment createMemberPayment() {
        return new MemberPayment();
    }

    /**
     * Create an instance of {@link Feedback }
     * 
     */
    public Feedback createFeedback() {
        return new Feedback();
    }

    /**
     * Create an instance of {@link MonthlyCustomerReport }
     * 
     */
    public MonthlyCustomerReport createMonthlyCustomerReport() {
        return new MonthlyCustomerReport();
    }

    /**
     * Create an instance of {@link AdhocEvent }
     * 
     */
    public AdhocEvent createAdhocEvent() {
        return new AdhocEvent();
    }

    /**
     * Create an instance of {@link Combine }
     * 
     */
    public Combine createCombine() {
        return new Combine();
    }

    /**
     * Create an instance of {@link TenantReceipt }
     * 
     */
    public TenantReceipt createTenantReceipt() {
        return new TenantReceipt();
    }

    /**
     * Create an instance of {@link TenantVenue }
     * 
     */
    public TenantVenue createTenantVenue() {
        return new TenantVenue();
    }

    /**
     * Create an instance of {@link TransactionRecord }
     * 
     */
    public TransactionRecord createTransactionRecord() {
        return new TransactionRecord();
    }

    /**
     * Create an instance of {@link BuyShowTicketItem }
     * 
     */
    public BuyShowTicketItem createBuyShowTicketItem() {
        return new BuyShowTicketItem();
    }

    /**
     * Create an instance of {@link Negotiator }
     * 
     */
    public Negotiator createNegotiator() {
        return new Negotiator();
    }

    /**
     * Create an instance of {@link Timeslot }
     * 
     */
    public Timeslot createTimeslot() {
        return new Timeslot();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContract() {
        return new Contract();
    }

    /**
     * Create an instance of {@link DetailShopOrder }
     * 
     */
    public DetailShopOrder createDetailShopOrder() {
        return new DetailShopOrder();
    }

    /**
     * Create an instance of {@link BuyHotelItem }
     * 
     */
    public BuyHotelItem createBuyHotelItem() {
        return new BuyHotelItem();
    }

    /**
     * Create an instance of {@link Picture }
     * 
     */
    public Picture createPicture() {
        return new Picture();
    }

    /**
     * Create an instance of {@link RsOrder }
     * 
     */
    public RsOrder createRsOrder() {
        return new RsOrder();
    }

    /**
     * Create an instance of {@link TableType }
     * 
     */
    public TableType createTableType() {
        return new TableType();
    }

    /**
     * Create an instance of {@link Room }
     * 
     */
    public Room createRoom() {
        return new Room();
    }

    /**
     * Create an instance of {@link Membership }
     * 
     */
    public Membership createMembership() {
        return new Membership();
    }

    /**
     * Create an instance of {@link ShopItem }
     * 
     */
    public ShopItem createShopItem() {
        return new ShopItem();
    }

    /**
     * Create an instance of {@link MemberAccount }
     * 
     */
    public MemberAccount createMemberAccount() {
        return new MemberAccount();
    }

    /**
     * Create an instance of {@link Restaurant }
     * 
     */
    public Restaurant createRestaurant() {
        return new Restaurant();
    }

    /**
     * Create an instance of {@link RtReservation }
     * 
     */
    public RtReservation createRtReservation() {
        return new RtReservation();
    }

    /**
     * Create an instance of {@link RentalReports }
     * 
     */
    public RentalReports createRentalReports() {
        return new RentalReports();
    }

    /**
     * Create an instance of {@link TenantBill }
     * 
     */
    public TenantBill createTenantBill() {
        return new TenantBill();
    }

    /**
     * Create an instance of {@link GuestAddress }
     * 
     */
    public GuestAddress createGuestAddress() {
        return new GuestAddress();
    }

    /**
     * Create an instance of {@link Stay }
     * 
     */
    public Stay createStay() {
        return new Stay();
    }

    /**
     * Create an instance of {@link ReservationState }
     * 
     */
    public ReservationState createReservationState() {
        return new ReservationState();
    }

    /**
     * Create an instance of {@link ItemOrder }
     * 
     */
    public ItemOrder createItemOrder() {
        return new ItemOrder();
    }

    /**
     * Create an instance of {@link HotelFoodMaterial }
     * 
     */
    public HotelFoodMaterial createHotelFoodMaterial() {
        return new HotelFoodMaterial();
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link AvailableTable }
     * 
     */
    public AvailableTable createAvailableTable() {
        return new AvailableTable();
    }

    /**
     * Create an instance of {@link Address }
     * 
     */
    public Address createAddress() {
        return new Address();
    }

    /**
     * Create an instance of {@link RoomType }
     * 
     */
    public RoomType createRoomType() {
        return new RoomType();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link Guest }
     * 
     */
    public Guest createGuest() {
        return new Guest();
    }

    /**
     * Create an instance of {@link ShopOrder }
     * 
     */
    public ShopOrder createShopOrder() {
        return new ShopOrder();
    }

    /**
     * Create an instance of {@link Hotel }
     * 
     */
    public Hotel createHotel() {
        return new Hotel();
    }

    /**
     * Create an instance of {@link BuyCardValue }
     * 
     */
    public BuyCardValue createBuyCardValue() {
        return new BuyCardValue();
    }

    /**
     * Create an instance of {@link ResReservation }
     * 
     */
    public ResReservation createResReservation() {
        return new ResReservation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopItemByItemIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShopItemByItemIdResponse")
    public JAXBElement<GetShopItemByItemIdResponse> createGetShopItemByItemIdResponse(GetShopItemByItemIdResponse value) {
        return new JAXBElement<GetShopItemByItemIdResponse>(_GetShopItemByItemIdResponse_QNAME, GetShopItemByItemIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateShopOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "createShopOrder")
    public JAXBElement<CreateShopOrder> createCreateShopOrder(CreateShopOrder value) {
        return new JAXBElement<CreateShopOrder>(_CreateShopOrder_QNAME, CreateShopOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDetailOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "createDetailOrderResponse")
    public JAXBElement<CreateDetailOrderResponse> createCreateDetailOrderResponse(CreateDetailOrderResponse value) {
        return new JAXBElement<CreateDetailOrderResponse>(_CreateDetailOrderResponse_QNAME, CreateDetailOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTablesByRestaurantId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getTablesByRestaurantId")
    public JAXBElement<GetTablesByRestaurantId> createGetTablesByRestaurantId(GetTablesByRestaurantId value) {
        return new JAXBElement<GetTablesByRestaurantId>(_GetTablesByRestaurantId_QNAME, GetTablesByRestaurantId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddShoppingMallItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "addShoppingMallItem")
    public JAXBElement<AddShoppingMallItem> createAddShoppingMallItem(AddShoppingMallItem value) {
        return new JAXBElement<AddShoppingMallItem>(_AddShoppingMallItem_QNAME, AddShoppingMallItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteShopItemByItemId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "deleteShopItemByItemId")
    public JAXBElement<DeleteShopItemByItemId> createDeleteShopItemByItemId(DeleteShopItemByItemId value) {
        return new JAXBElement<DeleteShopItemByItemId>(_DeleteShopItemByItemId_QNAME, DeleteShopItemByItemId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberAccountByNFCCardNumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getMemberAccountByNFCCardNumResponse")
    public JAXBElement<GetMemberAccountByNFCCardNumResponse> createGetMemberAccountByNFCCardNumResponse(GetMemberAccountByNFCCardNumResponse value) {
        return new JAXBElement<GetMemberAccountByNFCCardNumResponse>(_GetMemberAccountByNFCCardNumResponse_QNAME, GetMemberAccountByNFCCardNumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShoppingMallItemsByShopId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShoppingMallItemsByShopId")
    public JAXBElement<GetShoppingMallItemsByShopId> createGetShoppingMallItemsByShopId(GetShoppingMallItemsByShopId value) {
        return new JAXBElement<GetShoppingMallItemsByShopId>(_GetShoppingMallItemsByShopId_QNAME, GetShoppingMallItemsByShopId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShoppingMallItemsByShopIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShoppingMallItemsByShopIdResponse")
    public JAXBElement<GetShoppingMallItemsByShopIdResponse> createGetShoppingMallItemsByShopIdResponse(GetShoppingMallItemsByShopIdResponse value) {
        return new JAXBElement<GetShoppingMallItemsByShopIdResponse>(_GetShoppingMallItemsByShopIdResponse_QNAME, GetShoppingMallItemsByShopIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRestaurantTable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "addRestaurantTable")
    public JAXBElement<AddRestaurantTable> createAddRestaurantTable(AddRestaurantTable value) {
        return new JAXBElement<AddRestaurantTable>(_AddRestaurantTable_QNAME, AddRestaurantTable.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRestaurantTableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "addRestaurantTableResponse")
    public JAXBElement<AddRestaurantTableResponse> createAddRestaurantTableResponse(AddRestaurantTableResponse value) {
        return new JAXBElement<AddRestaurantTableResponse>(_AddRestaurantTableResponse_QNAME, AddRestaurantTableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShopByIdResponse")
    public JAXBElement<GetShopByIdResponse> createGetShopByIdResponse(GetShopByIdResponse value) {
        return new JAXBElement<GetShopByIdResponse>(_GetShopByIdResponse_QNAME, GetShopByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateShopOrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "createShopOrderResponse")
    public JAXBElement<CreateShopOrderResponse> createCreateShopOrderResponse(CreateShopOrderResponse value) {
        return new JAXBElement<CreateShopOrderResponse>(_CreateShopOrderResponse_QNAME, CreateShopOrderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMemberAccountByNFCCardNum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getMemberAccountByNFCCardNum")
    public JAXBElement<GetMemberAccountByNFCCardNum> createGetMemberAccountByNFCCardNum(GetMemberAccountByNFCCardNum value) {
        return new JAXBElement<GetMemberAccountByNFCCardNum>(_GetMemberAccountByNFCCardNum_QNAME, GetMemberAccountByNFCCardNum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopItemByItemId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShopItemByItemId")
    public JAXBElement<GetShopItemByItemId> createGetShopItemByItemId(GetShopItemByItemId value) {
        return new JAXBElement<GetShopItemByItemId>(_GetShopItemByItemId_QNAME, GetShopItemByItemId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShopById")
    public JAXBElement<GetShopById> createGetShopById(GetShopById value) {
        return new JAXBElement<GetShopById>(_GetShopById_QNAME, GetShopById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTablesByRestaurantIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getTablesByRestaurantIdResponse")
    public JAXBElement<GetTablesByRestaurantIdResponse> createGetTablesByRestaurantIdResponse(GetTablesByRestaurantIdResponse value) {
        return new JAXBElement<GetTablesByRestaurantIdResponse>(_GetTablesByRestaurantIdResponse_QNAME, GetTablesByRestaurantIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDetailOrder }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "createDetailOrder")
    public JAXBElement<CreateDetailOrder> createCreateDetailOrder(CreateDetailOrder value) {
        return new JAXBElement<CreateDetailOrder>(_CreateDetailOrder_QNAME, CreateDetailOrder.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopItemByBarcodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShopItemByBarcodeResponse")
    public JAXBElement<GetShopItemByBarcodeResponse> createGetShopItemByBarcodeResponse(GetShopItemByBarcodeResponse value) {
        return new JAXBElement<GetShopItemByBarcodeResponse>(_GetShopItemByBarcodeResponse_QNAME, GetShopItemByBarcodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteShopItemByItemIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "deleteShopItemByItemIdResponse")
    public JAXBElement<DeleteShopItemByItemIdResponse> createDeleteShopItemByItemIdResponse(DeleteShopItemByItemIdResponse value) {
        return new JAXBElement<DeleteShopItemByItemIdResponse>(_DeleteShopItemByItemIdResponse_QNAME, DeleteShopItemByItemIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShopItemByBarcode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "getShopItemByBarcode")
    public JAXBElement<GetShopItemByBarcode> createGetShopItemByBarcode(GetShopItemByBarcode value) {
        return new JAXBElement<GetShopItemByBarcode>(_GetShopItemByBarcode_QNAME, GetShopItemByBarcode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddShoppingMallItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://common.soap/", name = "addShoppingMallItemResponse")
    public JAXBElement<AddShoppingMallItemResponse> createAddShoppingMallItemResponse(AddShoppingMallItemResponse value) {
        return new JAXBElement<AddShoppingMallItemResponse>(_AddShoppingMallItemResponse_QNAME, AddShoppingMallItemResponse.class, null, value);
    }

}
