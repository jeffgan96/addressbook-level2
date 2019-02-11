package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {

	private AddressBook unsortedAddressBook;
	
	private List<ReadOnlyPerson> sortedList;
	private List<ReadOnlyPerson> unsortedList;

	@Before
	public void setUp() throws Exception {
		Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
				new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
		Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
				new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
		Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
				new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
		Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
				new Email("david@grant.com", false), new Address("44H Define Road", false),
				Collections.emptySet());

		unsortedAddressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
		
		sortedList = TestUtil.createList(davidGrant, janeDoe, johnDoe, samDoe);
		unsortedList = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
	}

	@Test
	public void execute_sortList_returnsSortSuccessfulMessage() {
		assertSortBehaviour(unsortedAddressBook, unsortedList, sortedList);
	}

	/**
	 * Creates a new sort command.
	 */
	private SortCommand createSortCommand(AddressBook unsortedAddressBook, 
										  List<? extends ReadOnlyPerson> unsortedList) {

		SortCommand command = new SortCommand();
		command.setData(unsortedAddressBook, unsortedList);

		return command;
	}

	/**
	 * Executes the command, and checks that the execution was what we had expected.
	 */
	private void assertCommandBehaviour (SortCommand sortCommand, String expectedMessage,
										 List<ReadOnlyPerson> sortedList) {

		CommandResult result = sortCommand.execute();
		List<? extends ReadOnlyPerson> resultList = null;
		
		if (result.getRelevantPersons().isPresent()) {
			resultList = result.getRelevantPersons().get();
		}
		assertTrue(TestUtil.isIdentical((List<ReadOnlyPerson>)resultList, sortedList));
		
		assertEquals(expectedMessage, result.feedbackToUser);
	}
	
	/**
	 * Asserts that the sort works.
	 */
	private void assertSortBehaviour(AddressBook unsortedAddressBook, List<ReadOnlyPerson> unsortedList,
									 List<ReadOnlyPerson> sortedList) {

		String expectedMessage = Messages.MESSAGE_SORT_SUCCESSFUL;

		SortCommand command = createSortCommand(unsortedAddressBook, unsortedList);
		assertCommandBehaviour(command, expectedMessage, sortedList);
	}

}

