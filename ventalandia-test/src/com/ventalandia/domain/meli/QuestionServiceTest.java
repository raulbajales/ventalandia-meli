package com.ventalandia.domain.meli;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ventalandia.domain.Question;
import com.ventalandia.framework.http.HttpConnector;
import com.ventalandia.framework.http.HttpResponse;
import com.ventalandia.meli.pesistence.CountryRepository;
import com.ventalandia.meli.pesistence.CurrencyRepository;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.UserRepository;
import com.ventalandia.service.QuestionServiceImpl;

public class QuestionServiceTest {

	private HttpConnector httpConnector;
	private UserRepository userRepository;
	private ItemRepository itemRepository;
	private CountryRepository countryRepository;
	private CurrencyRepository currencyRepository;
	private QuestionServiceImpl questionService;

	@Before
	public void setup() {

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
		httpConnector = mock(HttpConnector.class);
		userRepository = mock(UserRepository.class);
		itemRepository = mock(ItemRepository.class);
		countryRepository = mock(CountryRepository.class);
		currencyRepository = mock(CurrencyRepository.class);

		questionService = new QuestionServiceImpl(httpConnector, gson, userRepository, itemRepository, countryRepository, currencyRepository);

	}

	@Test
	public void testGetQuestion() throws Exception {

		String questionId = "/questions/1234";
		long userId = 79450083l;
		String userResource = "/users/"+userId;
		String sellerResource = "/users/86898669";
		String countryResource = "/countries/AR";
		String itemResource = "/items/MLA430494065";
		String currencyResource = "/currencies/ARS";
		
		when(httpConnector.get(questionId)).thenReturn(new HttpResponse(200, getQuestionBody()));
		when(httpConnector.get(userResource)).thenReturn(new HttpResponse(200, getClientBody()));
		when(httpConnector.get(sellerResource)).thenReturn(new HttpResponse(200, getSellerBody()));
		when(httpConnector.get(countryResource)).thenReturn(new HttpResponse(200, getCountryBody()));
		when(httpConnector.get(itemResource)).thenReturn(new HttpResponse(200, getItemBody()));
		when(httpConnector.get(currencyResource)).thenReturn(new HttpResponse(200, getCurrencyBody()));

		Question question = questionService.getQuestionFromMeli(questionId,userId);
		
		Assert.assertNotNull(question);
		Assert.assertEquals("Hola, trabajan los sabados?",question.getText());
		Assert.assertEquals("Balanza Electronica Digital Para Cocina Daewoo International", question.getItem().getTitle());
		

	}

	private String getCurrencyBody() {
		return "{\"id\":\"ARS\",\"description\":\"Peso argentino\",\"symbol\":\"$\",\"decimal_places\":2}";
	}

	private String getItemBody(){
		return "{\"id\":\"MLA430494065\",\"site_id\":\"MLA\",\"title\":\"Balanza Electronica Digital Para Cocina Daewoo International\",\"subtitle\":\"" +
				"Spotcompras 6 Mes Gtia Factura A B Envios Todo El Pais Showroom Capfed\",\"seller_id\":86898669,\"category_id\":\"MLA10228\"" +
				",\"price\":130,\"base_price\":130,\"currency_id\":\"ARS\",\"initial_quantity\":12,\"available_quantity\":9,\"sold_quantity\":13,\"buying_mode\":" +
				"\"buy_it_now\",\"listing_type_id\":\"gold\",\"start_time\":\"2012-08-10T17:03:06.000Z\",\"stop_time\":\"2012-10-09T17:03:06.000Z\",\"condition\":\"new" +
				"\",\"permalink\":\"http://articulo.mercadolibre.com.ar/MLA-430494065-balanza-electronica-digital-para-cocina-daewoo-international-_JM\",\"thumbnail\":" +
				"\"http://img2.mlstatic.com/s_MLA_v_I_f_2549515898_032012.jpg\",\"pictures\":[{\"id\":\"MLA2549515898_032012\",\"url\":\"http://img2.mlstatic.com" +
				"/s_MLA_v_O_f_2549515898_032012.jpg\",\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549515898_032012.jpg\",\"size\":" +
				"\"500x500\",\"max_size\":\"500x500\",\"quality\":\"\"},{\"id\":\"MLA2549495257_032012\",\"url\":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549495257_032012.jpg\"" +
				",\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549495257_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\"," +
				"\"quality\":\"\"},{\"id\":\"MLA2549516247_032012\",\"url\":\"http://img1.mlstatic.com/s_MLA_v_O_f_2549516247_032012.jpg\",\"secure_url\":" +
				"\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549516247_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"},{\"id" +
				"\":\"MLA2549495420_032012\",\"url\":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549495420_032012.jpg\",\"secure_url\":\"https://www.mercadolibre." +
				"com/jm/img?s=MLA&v=O&f=2549495420_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"},{\"id\":\"MLA2549495673_03201" +
				"2\",\"url\":\"http://img2.mlstatic.com/s_MLA_v_O_f_2549495673_032012.jpg\",\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O" +
				"&f=2549495673_032012.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"},{\"id\":\"MLA2549516809_032012\",\"url\":\"http://img1.mls" +
				"tatic.com/s_MLA_v_O_f_2549516809_032012.jpg\",\"secure_url\":\"https://www.mercadolibre.com/jm/img?s=MLA&v=O&f=2549516809_03201" +
				"2.jpg\",\"size\":\"500x375\",\"max_size\":\"640x480\",\"quality\":\"\"}],\"video_id\":null,\"descriptions\":[{\"id\":\"MLA430494065-274770877\"}],\"accepts_" +
				"mercadopago\":true,\"non_mercado_pago_payment_methods\":[{\"id\":\"MLAVE\",\"description\":\"Visa Electron\",\"type\":\"D\"},{\"id\":\"MLATB\",\"desc" +
				"ription\":\"Transferencia Bancaria\",\"type\":\"G\"},{\"id\":\"MLAVS\",\"description\":\"Visa\",\"type\":\"C\"},{\"id\":\"MLAMO\",\"description\":\"Efectivo\",\"typ" +
				"e\":\"G\"}],\"shipping\":{\"profile_id\":null,\"mode\":\"custom\",\"local_pick_up\":true,\"free_shipping\":false,\"methods\":[],\"dimensions\":null},\"seller_ad" +
				"dress\":{\"id\":57018813,\"comment\":\"\",\"address_line\":\"\",\"zip_code\":\"\",\"city\":{\"id\":\"\",\"name\":\"Villa Urquiza\"},\"state\":{\"id\":\"AR-C\",\"name\":" +
				"\"Capital Federal\"},\"country\":{\"id\":\"AR\",\"name\":\"Argentina\"},\"latitude\":\"\",\"longitude\":\"\"},\"seller_contact\":null,\"location\":null,\"geolocation\":{" +
				"\"latitude\":\"\",\"longitude\":\"\"},\"coverage_areas\":[],\"attributes\":[],\"variations\":[],\"status\":\"active\",\"sub_status\":[],\"tags\":[],\"warranty\":\"6 mes" +
				"es Gtia -  Ver nuestros terminos de Garantia -  su oferta implica aceptacion de la misma\",\"catalog_product_id\":null,\"parent_item_id\":\"MLA4250564" +
				"55\",\"date_created\":\"2012-08-10T17:03:06.000Z\",\"last_updated\":\"2012-09-12T20:47:15.000Z\"}";
	}
	
	private String getCountryBody(){
		return "{\"id\":\"AR\",\"name\":\"Argentina\",\"locale\":\"es_AR\",\"currency_id\":\"ARS\",\"decimal_separator\":\",\",\"thousands_separator\":\".\",\"time_zone" +
				"\":\"GMT-03:00\",\"geo_information\":{\"location\":{\"latitude\":-38.416097,\"longitude\":-63.616672}},\"states\":[{\"id\":\"AR-B\",\"name\":\"Buenos Aires" +
				"\"},{\"id\":\"AR-C\",\"name\":\"Capital Federal\"},{\"id\":\"AR-K\",\"name\":\"Catamarca\"},{\"id\":\"AR-H\",\"name\":\"Chaco\"},{\"id\":\"AR-U\",\"name\":\"Chubut" +
				"\"},{\"id\":\"AR-W\",\"name\":\"Corrientes\"},{\"id\":\"AR-X\",\"name\":\"Córdoba\"},{\"id\":\"AR-E\",\"name\":\"Entre Ríos\"},{\"id\":\"AR-P\",\"name\":\"Formosa" +
				"\"},{\"id\":\"AR-Y\",\"name\":\"Jujuy\"},{\"id\":\"AR-L\",\"name\":\"La Pampa\"},{\"id\":\"AR-F\",\"name\":\"La Rioja\"},{\"id\":\"AR-M\",\"name\":\"Mendoza\"},{\"id\":" +
				"\"AR-N\",\"name\":\"Misiones\"},{\"id\":\"AR-Q\",\"name\":\"Neuqu�n\"},{\"id\":\"AR-R\",\"name\":\"Río Negro\"},{\"id\":\"AR-A\",\"name\":\"Salta\"},{\"id\":\"AR-J" +
				"\",\"name\":\"San Juan\"},{\"id\":\"AR-D\",\"name\":\"San Luis\"},{\"id\":\"AR-Z\",\"name\":\"Santa Cruz\"},{\"id\":\"AR-S\",\"name\":\"Santa Fe\"},{\"id\":\"AR-G\",\"" +
				"name\":\"Santiago del Estero\"},{\"id\":\"AR-V\",\"name\":\"Tierra del Fuego \"},{\"id\":\"AR-T\",\"name\":\"Tucumán\"}]}";
	}
	
	private String getSellerBody() {

		return "{\"id\":86898669,\"nickname\":\"SPOTCOMPRAS\",\"registration_date\":\"2005-11-08T14:41:10.000-04:00\",\"country_id\":\"AR\",\"user_type\":" +
				"\"normal\",\"logo\":null,\"points\":19546,\"site_id\":\"MLA\",\"permalink\":\"http://perfil.mercadolibre.com.ar/SPOTCOMPRAS\",\"seller_reputation\":{" +
				"\"level_id\":\"2_orange\",\"power_seller_status\":null,\"transactions\":{\"period\":\"3 months\",\"total\":2014,\"completed\":1770,\"canceled\":244,\"ratings" +
				"\":{\"positive\":0.99,\"negative\":0,\"neutral\":0}}},\"status\":{\"site_status\":\"active\"}}";
	}

	private String getClientBody() {
		return "{\"id\":79450083,\"nickname\":\"GERMANTANO\",\"registration_date\":\"2005-03-22T09:40:19.000-04:00\",\"country_id\":\"AR\"" +
				",\"user_type\":\"normal\",\"logo\":null,\"points\":11,\"site_id\":\"MLA\",\"permalink\":\"http://perfil.mercadolibre.com.ar/GERMANTANO\"," +
				"\"seller_reputation\":{\"level_id\":null,\"power_seller_status\":null,\"transactions\":{\"period\":\"historic\",\"total\":0,\"completed\":0,\"canceled\":0," +
				"\"ratings\":{\"positive\":0,\"negative\":0,\"neutral\":0}}},\"status\":{\"site_status\":\"active\"}}";
	}

	private String getQuestionBody() {
		return "{\"id\":2455498075,\"answer\":{\"date_created\":\"2012-08-31T14:59:46.000-04:00\",\"status\":\"ACTIVE\",\"text\":\"GERMANTANO  Hola ," +
				" si estamos de 10 a 13 el sabado. saludos.sd\"},\"date_created\":\"2012-08-31T14:54:15.000-04:00\",\"item_id\":\"MLA430494065\",\"seller_id" +
				"\":86898669,\"status\":\"ANSWERED\",\"text\":\"Hola, trabajan los sabados?\"}";
	}

}
