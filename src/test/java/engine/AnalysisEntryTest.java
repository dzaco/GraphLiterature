package engine;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class AnalysisEntryTest extends TestCase {

    @Test
    public void testStaticIndexes() {
        var v1 = new AnalysisEntry("v1", "v1");
        var v2 = new AnalysisEntry("v2", "v2");
        var v3 = new AnalysisEntry("v3", "v3");

        Assert.assertEquals(1, v1.getIndex());
        Assert.assertEquals(2, v2.getIndex());
        Assert.assertEquals(3, v3.getIndex());

    }
}