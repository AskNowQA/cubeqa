package org.aksw.autosparql.cube.property.scorer.temporal;

import static org.junit.Assert.*;
import org.aksw.autosparql.cube.Cube;
import org.aksw.autosparql.cube.property.ComponentProperty;
import org.joda.time.Instant;
import org.joda.time.Interval;
import org.junit.Test;

public class TemporalScorerTest
{

	@Test public void testYearScorer()
	{
		Cube cube = Cube.getInstance("finland-aid");
		ComponentProperty property = cube.properties.get("http://linkedspending.aksw.org/ontology/refYear");
		assertFalse(property.scorer.score("2005").isPresent());
		assertTrue(property.scorer.score("2006").isPresent());
		assertTrue(property.scorer.score("2007").isPresent());
		assertTrue(property.scorer.score("2011").isPresent());
		assertFalse(property.scorer.score("2012").isPresent());
	}

	@Test public void testDateScorer()
	{
	}

	@Test public void testParseAsYear()
	{
		Interval y2014 = TemporalScorer.parseAsYear("2014");
		assertFalse(y2014.contains(Instant.parse("2013-12-31")));
		assertTrue(y2014.contains(Instant.parse("2014-01-01")));
		assertTrue(y2014.contains(Instant.parse("2014-12-31")));
		assertFalse(y2014.contains(Instant.parse("2015-01-01")));
	}

	@Test public void testParseAsDate()
	{
		Interval d20140101 = TemporalScorer.parseAsDate("2014-01-01");
		assertFalse(d20140101.contains(Instant.parse("2013-12-31")));
		assertTrue(d20140101.contains(Instant.parse("2014-01-01")));
		assertFalse(d20140101.contains(Instant.parse("2014-01-02")));
		assertFalse(d20140101.contains(Instant.parse("2015-01-01")));
	}

}