<?xml version = "1.0" encoding = "ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method = "xml" encoding = "UTF-8" indent = "yes" version = "1.0"/>
    <xsl:template match = "/">
<xsl:text>
</xsl:text>
        <entries>
        <xsl:apply-templates select = "entries"/>
        </entries>
    </xsl:template>

    <xsl:template match = "entries/entry">
        <entry>
        <xsl:attribute name="entry">
            <xsl:value-of select = "field"/>
        </xsl:attribute>
        </entry>
    </xsl:template>

</xsl:stylesheet>