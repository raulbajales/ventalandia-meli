package com.ventalandia.domain.meli;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.ventalandia.meli.api.notification.Item;

public class ItemTest {

	@Inject
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

	@Test
	public void testItemFromJson() throws Exception {

		String json = this.createItemContent();
		Item item = gson.fromJson(json, Item.class);
		assertNotNull(item);
		assertEquals("MLA430494065", item.getId());
		assertEquals("Balanza Electronica Digital Para Cocina Daewoo International", item.getTitle());
	}

	private String createItemContent() {

		return "{\"id\":\"MLA430494065\",\"site_id\":\"MLA\",\"title\":\"Balanza Electronica Digital Para Cocina " +
				"Daewoo International\",\"subtitle\":\"Spotcompras 6 Mes Gtia Factura A B Envios Todo El Pais Showroom " +
				"Capfed\",\"seller_id\":86898669,\"category_id\":\"MLA10228\",\"price\":130,\"base_price\":130,\"currency_id\"" +
				":\"ARS\",\"initial_quantity\":12,\"available_quantity\":10,\"sold_quantity\":12,\"buying_mode\":\"buy_it_now\"," +
				"\"listing_type_id\":\"gold\",\"start_time\":\"2012-08-10T17:03:06.000Z\",\"stop_time\":\"2012-10-09T17:03:06.000Z\"," +
				"\"condition\":\"new\",\"permalink\":\"http://articulo.mercadolibre.com.ar/MLA-430494065-balanza-electronica-digital-para-cocina-daewoo-international-_JM\",\"thumbnail\":" +
				"\"http://img2.mlstatic.com/s_MLA_v_I_f_2549515898_032012.jpg\",\"pictures\":[{\"id\":\"MLA2549515898_032012\",\"url\"" +
				":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549515898_032012.jpg\",\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549515898_032012.jpg\"," +
				"\"size\":\"500x500\",\"max_size\":\"500x500\",\"quality\":\"\"},{\"id\":\"MLA2549495257_032012\",\"url\":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549495257_032012.jpg\"," +
				"\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549495257_032012.jpg\",\"size\":\"500x375\",\"max_size\":" +
				"\"640x480\",\"quality\":\"\"},{\"id\":\"MLA2549516247_032012\",\"url\":\"http://img1.mlstatic.com/s_MLA_v_O_f_2549516247_032012.jpg\",\"secure_url\":" +
				"\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549516247_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"},{\"id\":" +
				"\"MLA2549495420_032012\",\"url\":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549495420_032012.jpg\",\"secure_url\":" +
				"\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549495420_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"}," +
				"{\"id\":\"MLA2549495673_032012\",\"url\":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549495673_032012.jpg\",\"secure_url\":\"https://www.mercadolibre.com/jm" +
				"/img?s=MLA&v=O&f=2549495673_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"},{\"id\":\"MLA2549516809_032012\",\"url\":\"http" +
				"://img1.mlstatic.com/s_MLA_v_O_f_2549516809_032012.jpg\",\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549516809_032012.jpg\",\"size\":" +
				"\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"}],\"video_id\":null,\"descriptions\":[{\"id\":\"MLA430494065-274770877\"}],\"accepts_mercadopago\":true" +
				",\"non_mercado_pago_payment_methods\":[{\"id\":\"MLAVE\",\"description\":\"Visa Electron\",\"type\":\"D\"},{\"id\":\"MLATB\",\"description\":\"Transferencia Bancaria\"" +
				",\"type\":\"G\"},{\"id\":\"MLAVS\",\"description\":\"Visa\",\"type\":\"C\"},{\"id\":\"MLAMO\",\"description\":\"Efectivo\",\"type\":\"G\"}],\"shipping\":" +
				"{\"profile_id\":\"\",\"local_pick_up\":true,\"free_shipping\":false},\"seller_address\":{\"id\":57018813,\"comment\":\"\",\"address_line\":\"\",\"zip_code\"" +
				":\"\",\"city\":{\"id\":\"\",\"name\":\"Villa Urquiza\"},\"state\":{\"id\":\"AR-C\",\"name\":\"Capital Federal\"},\"country\":{\"id\":\"AR\",\"name\":\"Argentina" +
				"\"},\"latitude\":\"\",\"longitude\":\"\"},\"seller_contact\":null,\"location\":null,\"geolocation\":{\"latitude\":\"\",\"longitude\":\"\"},\"coverage_areas\":[]," +
				"\"attributes\":[],\"variations\":[],\"status\":\"active\",\"sub_status\":[],\"tags\":[\"dragged_bids_and_visits\"],\"warranty\":\"6 meses Gtia -  Ver nuestros " +
				"terminos de Garantia -  su oferta implica aceptacion de la misma\",\"catalog_product_id\":null,\"parent_item_id\":\"MLA425056455\",\"date_created\":\"2012-08-10T17" +
				":03:06.000Z\",\"last_updated\":\"2012-09-01T23:36:05.000Z\"}";
	}

}
