package ir.maktab.domain;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


//@NamedEntityGraph(name = Customer.FETCH_TWEET_AND_COMMENT,
//			attributeNodes = {
//					@NamedAttributeNode(value = "tweets", subgraph = "commentOfTweet"),
//			},
//			subgraphs = {
//					@NamedSubgraph(name = "commentOfTweet",
//							attributeNodes = {
//									@NamedAttributeNode(value = "comments")
//							}
//						)
//			}
//
//		)
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
	
	public static final String FETCH_TWEET_AND_COMMENT = "FETCH_TWEET_AND_COMMENT";
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Account> accounts = new HashSet<>();

	public Customer(String username, String password, String firstName,
			String lastName) {
		super.setUsername(username);
		super.setPassword(password);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		//super.setIsActive(isActive);
		//this.setBirthday(birthday);
	}
	
	@Override
	public String toString() {
		return  "id='" + getId() + "' " +
                "firstName= '" + getFirstName() + "' " +
                "lastName= '" + getLastName() + "' " +
                "username= '" + getUsername() + "' " +
                "password= '" + getPassword() + "' " +
                "birthday= '" + getBirthDate() + "' ";
	}
}
