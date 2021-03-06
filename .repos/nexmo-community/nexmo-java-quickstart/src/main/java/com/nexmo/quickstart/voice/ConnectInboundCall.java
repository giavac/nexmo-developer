/*
 * Copyright (c) 2011-2017 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.quickstart.voice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexmo.client.voice.ncco.ConnectNcco;
import com.nexmo.client.voice.ncco.Ncco;
import spark.Route;

import static com.nexmo.quickstart.Util.envVar;
import static spark.Spark.*;

public class ConnectInboundCall {
    public static void main(String[] args) throws Exception {
        ObjectMapper nccoMapper = new ObjectMapper();

        String YOUR_SECOND_NUMBER = envVar("YOUR_SECOND_NUMBER");

        /*
         * Route to answer incoming calls with an NCCO response.
         */
        Route answerRoute = (req, res) -> {
            ConnectNcco connect = new ConnectNcco(YOUR_SECOND_NUMBER);
            Ncco[] nccos = new Ncco[]{connect};

            res.type("application/json");
            return nccoMapper.writer().writeValueAsString(nccos);
        };

        port(3000);

        get("/webhooks/answer", answerRoute);
        post("/webhooks/answer", answerRoute);
    }
}
