package com.ventalandia.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Answer;
import com.ventalandia.domain.Country;
import com.ventalandia.domain.Currency;
import com.ventalandia.domain.Item;
import com.ventalandia.domain.Question;
import com.ventalandia.domain.User;
import com.ventalandia.framework.http.HttpConnector;
import com.ventalandia.framework.http.HttpResponse;
import com.ventalandia.meli.pesistence.CountryRepository;
import com.ventalandia.meli.pesistence.CurrencyRepository;
import com.ventalandia.meli.pesistence.ItemRepository;
import com.ventalandia.meli.pesistence.UserRepository;

/**
 * 
 * @author gzanussi
 * 
 */
public class QuestionServiceImpl implements QuestionService {

	private HttpConnector httpConnector;
	private Gson gson;
	private UserRepository userRepository;
	private ItemRepository itemRepository;
	private CountryRepository countryRepository;
	private CurrencyRepository currencyRepository;

	@Inject
	public QuestionServiceImpl(HttpConnector httpConnector, Gson gson, UserRepository userRepository, ItemRepository itemRepository, CountryRepository countryRepository,
			CurrencyRepository currencyRepository) {
		super();
		this.httpConnector = httpConnector;
		this.gson = gson;
		this.userRepository = userRepository;
		this.itemRepository = itemRepository;
		this.countryRepository = countryRepository;
		this.currencyRepository = currencyRepository;
	}

	/**
	 * Gets question from MELI service.
	 */
	@Override
	public Question getQuestionFromMeli(String questionId,long userId) {

		Question question = new Question();
		User client = getUser(userId);
		question.setClient(client);
		
		com.ventalandia.meli.api.notification.Question questionAPI = getEntityFromMELI(questionId, com.ventalandia.meli.api.notification.Question.class);
		fillQuestion(questionAPI, question);
		return question;

	}

	/**
	 * 
	 * @param answer
	 * @return
	 */
	private Answer getAnswer(com.ventalandia.meli.api.notification.Answer answer) {

		Answer answerResult = new Answer();
		answerResult.setCreationDate(answer.getDate_created());
		answerResult.setStatus(answer.getStatus());
		answerResult.setText(answer.getText());
		return answerResult;
	}

	/**
	 * 
	 * @param item_id
	 * @return
	 */
	private Item getItem(String item_id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("meliId", item_id);
		List<Item> list = itemRepository.search(params);
		if (list.isEmpty()) {

			Item item = new Item();
			com.ventalandia.meli.api.notification.Item entityFromMELI = getEntityFromMELI("/items/" + item_id, com.ventalandia.meli.api.notification.Item.class);
			fillItem(entityFromMELI, item);
			return item;
		}

		return list.get(0);
	}

	/**
	 * 
	 * @param user_id
	 * @return
	 */
	private User getUser(long user_id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("meliId", user_id);
		List<User> list = userRepository.search(params);
		if (list.isEmpty()) {
			User user = new User();
			com.ventalandia.meli.api.notification.User userAPI = getEntityFromMELI("/users/" + user_id, com.ventalandia.meli.api.notification.User.class);
			fillUser(userAPI, user);
			return user;
		}

		return list.get(0);
	}

	/**
	 * 
	 * @param country_id
	 * @return
	 */
	private Country getCountry(String country_id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("meliId", country_id);

		List<Country> list = countryRepository.search(params);
		if (list.isEmpty()) {
			Country country = new Country();
			com.ventalandia.meli.api.notification.Country countryAPI = getEntityFromMELI("/countries/" + country_id, com.ventalandia.meli.api.notification.Country.class);
			fillCountry(countryAPI, country);
			return country;
		}
		return list.get(0);
	}

	/**
	 * 
	 * @param currency_id
	 * @return
	 */
	private Currency getCurrency(String currency_id) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("meliId", currency_id);

		List<Currency> list = currencyRepository.search(params);
		if (list.isEmpty()) {
			Currency currency = new Currency();
			com.ventalandia.meli.api.notification.Currency currencyAPI = getEntityFromMELI("/currencies/" + currency_id, com.ventalandia.meli.api.notification.Currency.class);
			fillCurrency(currencyAPI, currency);
			return currency;
		}
		return list.get(0);
	}

	/**
	 * 
	 * @param resource
	 * @param clazz
	 * @return
	 */
	private <T> T getEntityFromMELI(String resource, Class<T> clazz) {

		HttpResponse httpResponse = httpConnector.get(resource);

		if (httpResponse.getResponseCode() == 200) {
			String json = httpResponse.getResponseMessage();
			return gson.fromJson(json, clazz);
		} else {
			throw new RuntimeException("problems to get Entity " + clazz.getName() + " from MELI");
		}
	}

	/**
	 * 
	 * @param questionAPI
	 * @param question
	 */
	private void fillQuestion(com.ventalandia.meli.api.notification.Question questionAPI, Question question) {

		
		User seller = getUser(questionAPI.getSeller_id());
		Item item = getItem(questionAPI.getItem_id());

		if (questionAPI.isAnswered()) {
			question.setAnswer(getAnswer(questionAPI.getAnswer()));
		}

		
		question.setSeller(seller);
		question.setText(questionAPI.getText());
		question.setItem(item);
		question.setStatus(questionAPI.getStatus());
		question.setCreationDate(questionAPI.getDate_created());
		question.setMeliId(questionAPI.getId());

	}

	/**
	 * 
	 * @param userAPI
	 * @param user
	 */
	private void fillUser(com.ventalandia.meli.api.notification.User userAPI, User user) {

		Country country = getCountry(userAPI.getCountry_id());
		user.setCountry(country);
		user.setMeliId(userAPI.getId());
		user.setNickName(userAPI.getNickname());
		user.setRegistrationDate(userAPI.getRegistration_date());

	}

	/**
	 * 
	 * @param countryAPI
	 * @param country
	 */
	private void fillCountry(com.ventalandia.meli.api.notification.Country countryAPI, Country country) {

		country.setMeliId(countryAPI.getId());
		country.setName(countryAPI.getName());

	}

	/**
	 * 
	 * @param itemAPI
	 * @param item
	 */
	private void fillItem(com.ventalandia.meli.api.notification.Item itemAPI, Item item) {

		Currency currency = getCurrency(itemAPI.getCurrency_id());
		User seller = getUser(itemAPI.getSeller_id());
		
		item.setAvailableQuantity(itemAPI.getAvailable_quantity());
		item.setBasePrice(itemAPI.getBase_price());
		item.setCreationDate(itemAPI.getDate_created());
		item.setCurrency(currency);
		item.setInitialQuantity(itemAPI.getInitial_quantity());
		item.setLastUpdated(itemAPI.getLast_updated());
		item.setPrice(itemAPI.getPrice());
		item.setSeller(seller);
		item.setSoldQuantity(itemAPI.getSold_quantity());
		item.setSubTitle(itemAPI.getSubtitle());
		item.setTitle(itemAPI.getTitle());

	}

	/**
	 * 
	 * @param currencyAPI
	 * @param currency
	 */
	private void fillCurrency(com.ventalandia.meli.api.notification.Currency currencyAPI, Currency currency) {

		currency.setDecimalPlaces(currencyAPI.getDecimal_places());
		currency.setDescription(currencyAPI.getDescription());
		currency.setMeliId(currencyAPI.getId());
		currency.setSymbol(currencyAPI.getSymbol());

	}

}
