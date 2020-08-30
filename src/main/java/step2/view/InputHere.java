package step2.view;

import step2.domain.Coordinate;
import step2.domain.ladder.LadderHeight;
import step2.domain.participant.Participant;
import step2.domain.participant.Participants;
import step2.dto.GameConstructData;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static step2.util.StringUtil.splitByDelimiter;

public class InputHere {

	private static final String INPUT_SHOULD_INTEGER = "자연수로 입력 해 주세요.";
	private static final String PLEASE_INPUT_PARTICIPANTS_NAME = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
	private static final String PLEASE_INPUT_LADDER_HEIGHT = "최대 사다리 높이는 몇 개인가요?";
	private static final Scanner SCANNER = new Scanner(System.in);

	private InputHere() {}

	public static GameConstructData getGameConstructData() {
		Participants participants = getParticipants();
		LadderHeight ladderHeight = getLadderHeight();
		return new GameConstructData(participants, ladderHeight);
	}

	private static Participants getParticipants() {
		System.out.println(PLEASE_INPUT_PARTICIPANTS_NAME);
		String[] names = getStringArray();
		List<Participant> participants = IntStream.range(0, names.length)
											.mapToObj(index -> new Participant(names[index], new Coordinate(index)))
											.collect(Collectors.toUnmodifiableList());
		return new Participants(participants);
	}

	private static LadderHeight getLadderHeight() {
		System.out.println(PLEASE_INPUT_LADDER_HEIGHT);
		return new LadderHeight(getIntValue());
	}

	private static String[] getStringArray() {
		String input = getStringValue();
		return splitByDelimiter(input);
	}

	private static int getIntValue() {
		try {
			return SCANNER.nextInt();
		} catch (InputMismatchException e) {
			throw new IllegalArgumentException(INPUT_SHOULD_INTEGER, e);
		} finally {
			SCANNER.nextLine();
		}
	}

	private static String getStringValue() {
		return SCANNER.nextLine();
	}
}
