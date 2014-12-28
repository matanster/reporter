package controllers

import models._
import play.api._
import play.api.mvc._
import play.api.db.slick._
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.meta._
import play.api.http.MimeTypes

trait Match {
  type Match = (String, String, String, String, String, String, Boolean, String)

  // slick class binding definition
  class Matches(tag: Tag) extends Table[Match](tag, "Matches") {
    def docName = column[String]("docName")
    def runID = column[String]("runID")
    def sentence = column[String]("sentence", O.Length(20000,varying=true), O.DBType("binary"))
    def matchPattern = column[String]("matchPattern")
    def locationActual = column[String]("locationActual")
    def locationTest = column[String]("locationTest")    
    def isFinalMatch = column[Boolean]("fullMatch")
    def matchIndication = column[String]("matchIndication")
    def * = (runID, docName, sentence, matchPattern, locationTest, locationActual, isFinalMatch, matchIndication)
  }
  
  // the table handle
  val matches = TableQuery[Matches]
}

object Application extends Controller with Match {

  def index = Action { implicit request =>
    println()
    Ok(s"app is up, got request [$request]")
  }
 
  def showExtractFoundation(runID: String, article: String) = DBAction { implicit request =>
    import play.api.libs.json._
    val rows = matches.filter(_.runID === runID).filter(_.docName === s"${article}.xml").list
    val content: List[Match1] = rows.map(r => Match1(r._1, r._2, r._3, r._4, r._5, r._6, r._7, r._8)).filter(_.isFinalMatch)
    val runIDs = List("ubuntu-2014-12-15 21:09:52.072", "ubuntu-2014-12-21 09:04:30.084")
    //println(content)
    println(runIDs)
    Ok(views.html.showExtractFoundation(runIDs, runID, article, content))
    //Ok(views.html.showExtractFoundation(Json.toJson(runIDs), runID, article, content))
  }
 
  def showOriginal(article: String) = Action { implicit request =>
    val original = s"../data/pdf/0-input/${article}.pdf"
    println(s"serving $original")
    Ok.sendFile(content = new java.io.File(original),
                inline = true) // as per https://www.playframework.com/documentation/2.1.3/ScalaStream
  }
}