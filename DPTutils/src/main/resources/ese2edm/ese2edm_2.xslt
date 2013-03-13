<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:dc="http://purl.org/dc/elements/1.1/"
                xmlns:dcterms="http://purl.org/dc/terms/"
                xmlns:edm="http://www.europeana.eu/schemas/edm/"
                xmlns:enrichment="http://www.europeana.eu/schemas/edm/enrichment"
                xmlns:europeana="http://www.europeana.eu/schemas/ese/"
                xmlns:oai="http://www.openarchives.org/OAI/2.0/"
                xmlns:ore="http://www.openarchives.org/ore/terms/"
                xmlns:owl="http://www.w3.org/2002/07/owl#"
                xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
                xmlns:skos="http://www.w3.org/2004/02/skos/core#"
                xmlns:wgs84="http://www.w3.org/2003/01/geo/wgs84_pos#"
                xpath-default-namespace="http://www.europeana.eu/schemas/ese/"
                xmlns:fn="http://www.w3.org/2005/xpath-functions"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                exclude-result-prefixes="xlink fn"
                version="2.0">
<xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <xsl:template match="skos:Concept[position() = 1]" priority="3">
        <xsl:for-each-group select="/rdf:RDF/skos:Concept" group-by="@rdf:about">
            <skos:Concept>
                <xsl:attribute name="rdf:about" select="current-group()[1]/@rdf:about"/>
                <xsl:copy-of select="current-group()[1]/dc:title"/>
                <xsl:for-each-group select="current-group()" group-by="if(dcterms:hasPart/text()) then dcterms:hasPart/text() else 'nothing'">
                    <xsl:copy-of select="current-group()[1]/dcterms:hasPart" />
                </xsl:for-each-group>
            </skos:Concept>
        </xsl:for-each-group>
        <xsl:apply-templates />
    </xsl:template>


    <xsl:template match="skos:Concept" priority="2"/>
    <xsl:template match="skos:Concept//*" priority="2"/>

    <xsl:template match="node()|@*">
      <xsl:copy>
        <xsl:apply-templates select="node()|@*"/>
      </xsl:copy>
    </xsl:template>
    
</xsl:stylesheet>
