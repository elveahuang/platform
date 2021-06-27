package cn.elvea.platform.xapi.controller;

import cn.elvea.platform.xapi.http.XApiResponse;
import cn.elvea.platform.xapi.model.Statement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.elvea.platform.xapi.utils.XApiConstants.XAPI_CONTENT_TYPE;

/**
 * XApiStatementController
 *
 * @author elvea
 */
@Controller
@RequestMapping("/xAPI/statements")
@Tag(name = "XApi Statement Resource")
public class XApiStatementController extends XApiAbstractController {

    /**
     * GET Statements
     * <p>
     * This method is called to fetch a single Statement or multiple Statements. If the statementId or
     * voidedStatementId parameter is specified a single Statement is returned.
     * <p>
     * Otherwise returns: A StatementResult Object, a list of Statements in reverse chronological order based on
     * "stored" time, subject to permissions and maximum list length. If additional results are available, an IRL to
     * retrieve them will be included in the StatementResult Object.
     *
     * @param statementId       Id of Statement to fetch.
     * @param voidedStatementId Id of voided Statement to fetch.
     * @param agentJson         Filter, only return Statements for which the specified Agent or Group is the
     *                          Actor or Object of the Statement.
     * @param verb              Filter, only return Statements matching the specified Verb id.
     * @param activity          Filter, only return Statements for which the Object of the Statement
     *                          is an Activity with the specified id.
     * @param registration      Filter, only return Statements matching the specified registration id.
     *                          Note that although frequently a unique registration will be used for one Actor assigned
     *                          to one Activity, this cannot be assumed. If only Statements for a certain Actor or
     *                          Activity are required, those parameters also need to be specified.
     * @param relatedActivities Apply the Activity filter broadly. Include Statements for which the Object, any of the
     *                          context Activities, or any of those properties in a contained SubStatement match the
     *                          Activity parameter, instead of that parameter's normal behavior. Matching is defined
     *                          in the same way it is for the "activity" parameter.
     * @param relatedAgents     Apply the Agent filter broadly. Include Statements for which the Actor, Object,
     *                          Authority, Instructor, Team, or any of these properties in a contained SubStatement
     *                          match the Agent parameter, instead of that parameter's normal behavior. Matching is
     *                          defined in the same way it is for the "agent" parameter.
     * @param since             Only Statements stored since the specified Timestamp (exclusive) are returned.
     * @param until             Only Statements stored at or before the specified Timestamp are returned.
     * @param format            If ids, only include minimum information necessary in Agent, Activity, Verb and Group
     *                          Objects to identify them. For Anonymous Groups this means including the minimum
     *                          information needed to identify each member.
     *                          <p>
     *                          If exact, return Agent, Activity, Verb and Group Objects populated exactly as they were
     *                          when the Statement was received. An LRS requesting Statements for the purpose of
     *                          importing them would use a format of "exact" in order to maintain Statement
     *                          Immutability.
     *                          <p>
     *                          If canonical, return Activity Objects and Verbs populated with the canonical definition
     *                          of the Activity Objects and Display of the Verbs as determined by the LRS, after
     *                          applying the language filtering process defined below, and return the original Agent
     *                          and Group Objects as in "exact" mode.
     * @param attachments       If true, the LRS uses the multipart response format and includes all attachments as
     *                          described previously. If false, the LRS sends the prescribed response with Content-Type
     *                          application/json and does not send attachment data.
     * @param ascending         If true, return results in ascending order of stored time
     * @param limit             Maximum number of Statements to return. 0 indicates return the maximum the server
     *                          will allow.
     * @return Statement or Statement Result
     */
    @ResponseBody
    @GetMapping(produces = XAPI_CONTENT_TYPE)
    public XApiResponse<?> getStatement(@RequestParam(value = "statementId", required = false) String statementId,
                                        @RequestParam(value = "voidedStatementId ", required = false) String voidedStatementId,
                                        @RequestParam(value = "agent", required = false) String agentJson,
                                        @RequestParam(value = "verb", required = false) String verb,
                                        @RequestParam(value = "activity", required = false) String activity,
                                        @RequestParam(value = "registration", required = false) String registration,
                                        @RequestParam(value = "related_activities", required = false) Boolean relatedActivities,
                                        @RequestParam(value = "related_agents", required = false, defaultValue = "false") Boolean relatedAgents,
                                        @RequestParam(value = "since", required = false) String since,
                                        @RequestParam(value = "until", required = false) String until,
                                        @RequestParam(value = "format", required = false, defaultValue = "exact") String format,
                                        @RequestParam(value = "attachments", required = false, defaultValue = "false") Boolean attachments,
                                        @RequestParam(value = "ascending", required = false, defaultValue = "false") Boolean ascending,
                                        @RequestParam(value = "page", required = false, defaultValue = "0") String page,
                                        @RequestParam(value = "limit", required = false, defaultValue = "0") String limit) {
        if (!StringUtils.hasText(statementId) || !StringUtils.hasText(voidedStatementId)) {
            return XApiResponse.success(this.statementService.getStatement(statementId, voidedStatementId));
        } else {
            return XApiResponse.success(this.statementService.getStatements(agentJson,
                    verb,
                    activity,
                    registration,
                    relatedActivities,
                    relatedAgents,
                    since,
                    until,
                    format,
                    attachments,
                    ascending,
                    limit));
        }
    }

    /**
     * Stores a single Statement with the given id.
     */
    @ResponseBody
    @PutMapping
    public XApiResponse<?> putStatement(@RequestParam("statementId") String statementId,
                                        @RequestBody String json) throws Exception {
        Statement statement = new Statement(json);
        this.statementService.saveStatement(statementId, statement);
        return XApiResponse.success();
    }

    /**
     * Stores a Statement, or a set of Statements.
     */
    @ResponseBody
    @PostMapping
    public XApiResponse<?> postStatement(@RequestBody String json) throws Exception {
        List<Statement> statementList = Statement.fromJson(json);
        return XApiResponse.success(this.statementService.saveStatements(statementList));
    }

}
