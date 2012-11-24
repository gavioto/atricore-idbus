/*
 * Atricore IDBus
 *
 * Copyright (c) 2009-2012, Atricore Inc.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.atricore.idbus.authz.test

import org.specs2.mutable._
import org.atricore.idbus.authz._
import org.atricore.idbus.authz.Decisions._

import support.{Precompiler}
import util.Logging
import java.io.File

/**
 * XACML2 to Scala code generation tester.
 */
class CodeGenerationSpec extends Specification with Logging {


  "The authz service" should {
    "generate dsl correctly " in {

      "simple policy" in {


        val precompiler = new Precompiler()

        precompiler.sources = Array(new File(getClass().getResource("/policies").getFile))
        precompiler.workingDirectory = new File(this.getClass().getResource("/").getFile)
        precompiler.targetDirectory = new File(this.getClass().getResource("/").getFile)
        precompiler.execute

        val engine = new AuthorizationEngine(Array(new File(getClass().getResource("/policies").getFile))) {
          // lets output generated bytecode to the classes directory.
          override def bytecodeDirectory = {
            new File(this.getClass().getResource("/").getFile)
          }
        }

        val simpleDecisionRequest =
          DecisionRequest(
            Some(
              SubjectAttributes("urn:oasis:names:tc:xacml:1.0:subject-category:access-subject",
                List(
                  Attribute(
                    "urn:oasis:names:tc:xacml:1.0:subject:subject-id",
                    "med.example.com",
                    List(
                      AttributeValue(
                        "http://www.w3.org/2001/XMLSchema#string",
                        "Julius Hibbert"
                      ))
                  ),
                  Attribute(
                    "urn:oasis:names:tc:xacml:1.0:conformance-test:some-attribute",
                    "med.example.com",
                    List(
                      AttributeValue(
                        "http://www.w3.org/2001/XMLSchema#string",
                        "riddle me this"
                      ))
                  ),
                  Attribute(
                    "urn:oasis:names:tc:xacml:1.0:subject:authn-locality:ip-address",
                    "med.example.com",
                    List(
                      AttributeValue(
                        "urn:oasis:names:tc:xacml:2.0:data-type:ipAddress",
                        "192.168.1.1"
                      ))
                  )
                )
              )
            ),
            Some(
              ResourceAttributes(
                List(
                  Attribute(
                    "urn:oasis:names:tc:xacml:1.0:resource:resource-id",
                    "med.example.com",
                    List(
                      AttributeValue(
                        "http://www.w3.org/2001/XMLSchema#anyURI",
                        "http://medico.com/record/patient/BartSimpson"
                      ))
                  )
                )
              )
            ),
            Some(
              ActionAttributes(
                List(
                  Attribute(
                    "urn:oasis:names:tc:xacml:1.0:action:action-id",
                    "med.example.com",
                    List(
                      AttributeValue(
                        "http://www.w3.org/2001/XMLSchema#string",
                        "read"
                      ))
                  ),
                  Attribute(
                    "urn:oasis:names:tc:xacml:1.0:action:action-id",
                    "med.example.com",
                    List(
                      AttributeValue(
                        "http://www.w3.org/2001/XMLSchema#string",
                        "write"
                      ))
                  )
                )
              )
            )
          )


        val response = engine.evaluate("IIA001Policy.xml", simpleDecisionRequest )

        debug("response = " + response)

        response.decision mustEqual Permit
      }
    }



  }


}