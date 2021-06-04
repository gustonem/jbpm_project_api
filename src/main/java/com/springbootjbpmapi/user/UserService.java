package com.springbootjbpmapi.user;

import com.springbootjbpmapi.dto.UserDTO;
import com.springbootjbpmapi.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	private final ConfirmationTokenRepository confirmationTokenRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final ConfirmationTokenService confirmationTokenService;

	private final EmailSenderService emailSenderService;

	void sendConfirmationMail(String userMail, String token) {

		final SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userMail);
		mailMessage.setSubject("Mail Confirmation Link!");
		mailMessage.setFrom("<MAIL>");
		mailMessage.setText(
				"Thank you for registering. Please click on the below link to activate your account." + "http://localhost:8080/sign-up/confirm?token="
						+ token);

		emailSenderService.sendEmail(mailMessage);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		final Optional<User> optionalUser = userRepository.findByEmail(email);

		return optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist"));

	}

	public String signUpUser(UserDTO user) {

		User entity = new ModelMapper().map(user, User.class);

		final String encryptedPassword = bCryptPasswordEncoder.encode(entity.getPassword());
		entity.setPassword(encryptedPassword);
		final User createdUser = userRepository.save(entity);
		final ConfirmationToken confirmationToken = new ConfirmationToken(entity);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
//		sendConfirmationMail(user.getEmail(), confirmationToken.getConfirmationToken());
		return confirmationToken.getConfirmationToken();

	}

	public String confirmUser(String confirmationToken) {

		final ConfirmationToken tokenObject = confirmationTokenRepository
				.findConfirmationTokenByConfirmationToken(confirmationToken)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid token"));

		final User user = tokenObject.getUser();
		user.setEnabled(true);
		userRepository.save(user);
		confirmationTokenService.deleteConfirmationToken(tokenObject.getId());

		return "User confirmed";
	}
}
